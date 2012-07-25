package dimesSqlBasics;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import dimesVisGui.Details;

import localDataManagement.DataFileWriter;
import localDataManagement.IpOperations;
import localDataManagement.SourceData;
import localDataManagement.TargetData;

public class DimesDbOperationsMain
{
	//constants
	private static final int	guiDateYearIndex	= 2;
	private static final int	guiDateMonthIndex	= 1;
	private static final int	guiDateDayIndex		= 0;
	
	private static Connector	mainConnector	= null;
	private static Connector	secondConnector	= null;
	private static DimesQuery	latLongQuery	= null;
	private static String		mainSchema		= null;
	private static String		secondSchema	= null;
	private static String		ipsTblName		= null;
	private static SourceData	srcData			= null;

	public static String now()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public static String startDimesDbOperations(Details guiDetails)
	{
		String retVal = "Success";
		boolean noErrors = true;
		int additionIpRadioButtonVal = guiDetails.getAdditionalIpRadioButton();
		boolean isIpList = ((Details.addIpRadioOptAdd == additionIpRadioButtonVal) || (Details.addIpRadioOptOnlyAdd == additionIpRadioButtonVal));
		boolean isGenIps = ((Details.addIpRadioOptAdd == additionIpRadioButtonVal) || (Details.addIpRadioOptDontUse == additionIpRadioButtonVal));
		
		HashMap<Integer, String> excludedIps = null;
		
		boolean isExcludeList = (Details.excIpRadioOptUse == guiDetails.getExcludeIpRadioButton());
		
		if (isExcludeList)
		{
			int[][] excludedIpsMatrix = guiDetails.getExcludeIp();
			excludedIps = new HashMap<Integer, String>();
			String exIp;
			
			for (int exInd = 0; exInd < excludedIpsMatrix.length; exInd++)
			{
				exIp = IpOperations.intArrToIpStr(excludedIpsMatrix[exInd]);
				excludedIps.put(exInd, exIp);
			}
		}
									// from guiDetails
		// "SELECT SourceIP, SequenceNum, DestIP, avgTime "
		// +
		// "FROM dimes_results_2007.raw_res_main_2007, dimes_results_2007.raw_res_tr_2007 "
		// + "WHERE ((dimes_results_2007.raw_res_main_2007.reachedDest = 1) "
		// +
		// "AND (dimes_results_2007.raw_res_main_2007.DestAddress = dimes_results_2007.raw_res_tr_2007.hopAddress) "
		// +
		// "AND (dimes_results_2007.raw_res_main_2007.SequenceNum = dimes_results_2007.raw_res_tr_2007.MainSequenceNum) "
		// +
		// "AND (dimes_results_2007.raw_res_main_2007.SourceIP = '141.35.186.237')) "
		// + "LIMIT 150;";
		// '130.37.193.141'
		// get parameters of first connection from the GUI
		int mainPort = guiDetails.getFirstConnectionPort();
		String mainUserName = guiDetails.getFirstUserName();
		String mainPassword = guiDetails.getFirstPassword();
		mainSchema = guiDetails.getFirstSchemaName();
		String mainHostName = guiDetails.getFirstHostName();

		if ((null == mainUserName) || (mainUserName.equals("")))
		{
			mainUserName = "codeLimited";
		}

		mainConnector = new Connector(mainPort, mainUserName, mainPassword, mainSchema, mainHostName);
		mainConnector.connect();

		// get parameters of second connection from the GUI
		int secondPort = guiDetails.getSecondConnectionPort();
		String secondUserName = guiDetails.getSecondUserName();
		String secondPassword = guiDetails.getSecondPassword();
		secondSchema 		  = guiDetails.getSecondSchemaName();
		String secondHostName = guiDetails.getSecondHostName();

		if ((null == secondUserName) || (secondUserName.equals("")))
		{
			secondUserName = "codeLimited";
		}

		secondConnector = new Connector(secondPort, secondUserName, secondPassword, secondSchema, secondHostName);
		secondConnector.connect();

		// DimesQuery dimesQuery = new DimesQuery(QueryType.MainQuery,
		// "dimes_results_2007", "141.35.186.237", null,
		// DimesQueryTimeOption.Average, 100);
		// String mainQuery = dimesQuery.toString();

		String mainMainTable = guiDetails.getResMainTableName();
		String mainTracerouteTable = guiDetails.getResTraceTableName();
		String mainSrcIp = IpOperations.intArrToIpStr(guiDetails.getSourceIp());
		int mainDayOfYear = -1;
		if (guiDetails.getIsDate())
		{
			Calendar cal = Calendar.getInstance();
			cal.set(guiDetails.getDate()[guiDateYearIndex], guiDetails.getDate()[guiDateMonthIndex]-1, guiDetails.getDate()[guiDateDayIndex]);
			mainDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		}
		DimesQueryTimeOption mainTimeopt = guiDetails.getTimeChoiceRadioButton();
		int mainLimit = guiDetails.getLimit();
		ipsTblName = guiDetails.getIpsTblTableName();

		DimesQuery queryFromGui;
		String mainQuery;
		ResultSet rs;
		String dstIp = null;
		long seqNum = 0, measuredTime = 0;
		double lat = 0, lon = 0;
		srcData = new SourceData(mainSrcIp);
		TargetData td = null;
		ResultSet secRs;

		try
		{
			// non specific IPs, query for all available destinations
			if (isGenIps)
			{	
				queryFromGui = new DimesQuery(QueryType.MainQuery, mainSchema, mainMainTable, mainTracerouteTable, mainSrcIp, mainDayOfYear, mainTimeopt, mainLimit);
				mainQuery = queryFromGui.toString();

				System.out.println("Submit Main Statement Started at: " + now());
				rs = mainConnector.submitStatement(mainQuery);
				System.out.println("Submit Main Statement Ended at: " + now());

				while ((rs != null) && (rs.next()))
				{
					seqNum = rs.getLong(2);
					dstIp = rs.getString(3);
					measuredTime = rs.getLong(4);

					//insert destination if:
					//there is no exclude list
					//or
					//there is an exclude list and the destination is not in it 
					if ((!isExcludeList) || ((isExcludeList) && (!excludedIps.containsValue(dstIp))))
					{
						td = createTargetDataSingleIP(secondSchema, seqNum, dstIp, measuredTime);
						srcData.addTarget(seqNum, td);
					}
				}
			}
			
			//using specific IPs destinations list
			if (isIpList)
			{
				int[][] additionalIpsMatrix = guiDetails.getAdditionalIp();
				String[] additionalDestArr  = new String[additionalIpsMatrix.length];
				int index = 0;
				
				//prepare IP strings
				for (index = 0; index < additionalIpsMatrix.length; index++)
				{
					additionalDestArr[index] = IpOperations.intArrToIpStr(additionalIpsMatrix[index]);;
				}
				
				index = 0;
				boolean newTarget = true;
				
				while (index < additionalDestArr.length)
				{
					queryFromGui = new DimesQuery(QueryType.MainQuerySingleIp, mainSchema, mainMainTable, mainTracerouteTable, mainSrcIp, additionalDestArr[index], mainDayOfYear, mainTimeopt, mainLimit);
					mainQuery = queryFromGui.toString();
					rs = mainConnector.submitStatement(mainQuery);

					while ((rs != null) && (rs.next()))
					{
						// one target might have more than one occurrence
						if (newTarget)
						{
							newTarget = false;
							dstIp = rs.getString(3);
							secRs = getLatLong(dstIp);
							if ((secRs != null) && (secRs.next()))
							{
								lat = secRs.getDouble(1);
								lon = secRs.getDouble(2);
							}
							else
							{
								System.out.println("Error in main: Second result-set is empty for target: " + dstIp);
							}
						}

						seqNum = rs.getLong(2);
						measuredTime = rs.getLong(4);

						td = createTargetDataSingleIP(secondSchema, seqNum, dstIp, measuredTime, lat, lon);

						srcData.addTarget(seqNum, td);
					}

					index++;
					newTarget = true;
				}
			}

			// handle private source IP addresses
			if (srcData.isPrivateIpAddress())
			{
				DimesQuery traceHopsQuery = new DimesQuery(QueryType.TracerouteHopsQuery, seqNum, mainSchema, mainTracerouteTable);
				ResultSet traceHopsResultSet = mainConnector.submitStatement(traceHopsQuery.toString());
				
				if ((null != traceHopsResultSet) && (traceHopsResultSet.next()))
				{
					String nextHop = traceHopsResultSet.getString(3);
					while ((IpOperations.isPrivateIp(nextHop))	&&
						   (traceHopsResultSet != null)			&&
						   (traceHopsResultSet.next()))
					{
						nextHop = traceHopsResultSet.getString(3);
					}
					srcData.setRealSourceIP(nextHop);
					mainSrcIp = nextHop;
				}
				else
				{
					retVal = (!noErrors)?retVal:"Error in main: IP "+mainSrcIp+" is private, but trace hops query is empty or null";
					noErrors = false;
				}
			}

			// find latitude and longitude for source IP
			secRs = getLatLong(mainSrcIp);

			if ((secRs != null) && (secRs.next()))
			{
				srcData.setSourceLatitude(secRs.getDouble(1));
				srcData.setSourceLongitude(secRs.getDouble(2));
			}
			else
			{
				retVal = (!noErrors)?retVal:"Error in main: Second result-set is empty for source";
				noErrors = false;
				System.out.println(retVal);
			}

			if (0 == srcData.getNumOfTargets())
			{
				retVal = (!noErrors)?retVal:"Source IP "+srcData.getSourceIpAsString()+" has no results in specified Schema and Table";
				noErrors = false;
			}
			
			// write data to file
			DataFileWriter dfw = new DataFileWriter("C:\\javaTimesfile.txt");
			dfw.writeFullDataToFile(srcData, guiDetails.getFirstRadioButton(), guiDetails.getSecondRadioButton());
			dfw.closeDataFileWriter();
		}
		catch (SQLException sqlEx)
		{
			// handle any errors
			retVal = (!noErrors)?retVal:"SQLException @ main() \n" + "SQLException: "
									   + sqlEx.getMessage() + "\n" + "SQLState: "
									   + sqlEx.getSQLState() + "\n" + "VendorError: "
									   + sqlEx.getErrorCode();
			noErrors = false;
			System.out.println(retVal);
			sqlEx.printStackTrace();
			return retVal;
		}
		catch (IOException ioEx)
		{
			retVal = (!noErrors)?retVal:"IO error while writing to file:";
			noErrors = false;
			System.out.println(retVal);
			ioEx.printStackTrace();
			return retVal;
		}

		finally
		{
			mainConnector.closeConnection();
			secondConnector.closeConnection();
		}
		
		return retVal;
	}

	private static ResultSet getLatLong(String ip)
	{
		if (null == latLongQuery)
		{
			latLongQuery = new DimesQuery(QueryType.LatLongQuery, secondSchema, ipsTblName, ip);
		}
		else
		{
			latLongQuery.setLatLongIp(ip);
		}

		return secondConnector.submitStatement(latLongQuery.toString());
	}

	private static TargetData createTargetDataSingleIP(String secondSchema, long sequenceNum, String targetIp, long measuredTime) throws SQLException
	{
		TargetData localTargetData = new TargetData();
		ResultSet localResultSet;

		localTargetData.setTargetIP(targetIp);
		localTargetData.setMeasuredTime(measuredTime);

		localResultSet = getLatLong(targetIp);

		if ((localResultSet != null) && (localResultSet.next()))
		{
			localTargetData.setTargetLatitude(localResultSet.getDouble(1));
			localTargetData.setTargetLongitude(localResultSet.getDouble(2));
		}
		else
		{
			System.out
					.println("Error in main: Second result-set is empty for target: "
							+ targetIp + ", sequence number: " + sequenceNum);
		}

		return localTargetData;
	}

	private static TargetData createTargetDataSingleIP(String secondSchema,
			long sequenceNum, String targetIp, long measuredTime, double lat,
			double lon)
	{
		TargetData localTargetData = new TargetData();

		localTargetData.setTargetIP(targetIp);
		localTargetData.setMeasuredTime(measuredTime);

		localTargetData.setTargetLatitude(lat);
		localTargetData.setTargetLongitude(lon);

		return localTargetData;
	}
	
	public static String[] getDestStringsArray()
	{
		int numOfTargets = srcData.getNumOfTargets();
		String retVal[] = new String[numOfTargets];
		Long[] allSeqNums = srcData.getAllSequenceNumbers();
		
		for (int i=0; i<numOfTargets; i++)
		{
			retVal[i] = srcData.getTarget(allSeqNums[i]).getTargetIpAsString();
		}
		
		return retVal;
	}
}
