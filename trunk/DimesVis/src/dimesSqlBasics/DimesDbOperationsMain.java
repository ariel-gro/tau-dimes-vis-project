package dimesSqlBasics;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dimesVisGui.Details;

import localDataManagement.DataFileWriter;
import localDataManagement.SourceData;
import localDataManagement.TargetData;


public class DimesDbOperationsMain
{

	public static String now()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public static String startDimesDbOperations(Details guiDetails) throws IOException
	{
		String retVal = "Success";
//				  "SELECT SourceIP, SequenceNum, DestIP, avgTime "
//				+ "FROM dimes_results_2007.raw_res_main_2007, dimes_results_2007.raw_res_tr_2007 "
//				+ "WHERE ((dimes_results_2007.raw_res_main_2007.reachedDest = 1) "
//				+ "AND (dimes_results_2007.raw_res_main_2007.DestAddress = dimes_results_2007.raw_res_tr_2007.hopAddress) "
//				+ "AND (dimes_results_2007.raw_res_main_2007.SequenceNum = dimes_results_2007.raw_res_tr_2007.MainSequenceNum) "
//				+ "AND (dimes_results_2007.raw_res_main_2007.SourceIP = '141.35.186.237')) "
//				+ "LIMIT 150;";
		int    mainPort     = guiDetails.getConnectionPort();
		String mainUserName = guiDetails.getUserName();
		String mainPassword = guiDetails.getPassword();
		String mainSchema   = guiDetails.getSchemaName();
		String mainHostName = guiDetails.getHostName();
		
		if ((null == mainUserName) || (mainUserName.equals("")))
		{
			mainUserName = "codeLimited";
		}
		
		Connector mainConnector = new Connector(mainPort, mainUserName, mainPassword, mainSchema, mainHostName);
		mainConnector.connect();
		
		Connector secondConnector = new Connector(5551, "codeLimited", "", "DIMES_PLAYGROUND", "localhost");
		secondConnector.connect();

		//DimesQuery dimesQuery = new DimesQuery(QueryType.MainQuery, "dimes_results_2007", "141.35.186.237", null, DimesQueryTimeOption.Average, 100);
		//String     mainQuery  = dimesQuery.toString();
		
		int[]  mainSrcIpArr	= guiDetails.getSourceIp();
		String mainSrcIp	= mainSrcIpArr[0]+"."+mainSrcIpArr[1]+"."+mainSrcIpArr[2]+"."+mainSrcIpArr[3];
		String mainDate		= guiDetails.getDate()[2] + "";
		DimesQueryTimeOption mainTimeopt = guiDetails.getTimeChoiceRadioButton();
		int    mainLimit    = guiDetails.getLimit();
		
		DimesQuery queryFromGui = new DimesQuery(QueryType.MainQuery, mainSchema, mainSrcIp, mainDate, mainTimeopt, mainLimit);
		String mainQuery = queryFromGui.toString();
		
		System.out.println("Submit Main Statement Started at: " + now());
		ResultSet rs = mainConnector.submitStatement(mainQuery);
		System.out.println("Submit Main Statement Ended at: " + now());
		try
		{
			//System.out.println("SrcIP\tSequence#\tDestIP\tAvgTime\tDestLat\tDestLong");
			//System.out.println("-----------------------------------");
			
			String		srcIp = null, dstIp = null;
			long		seqNum = 0, avgTime = 0;
			SourceData	sd	= null;
			TargetData	td	= null;
			boolean		rsNotEmpty = rs.next();
			ResultSet	secRs;
			String		secQuery;
			DimesQuery latLongQuery = null;
			
			//get source data from results
			if (rsNotEmpty)
			{
				srcIp = rs.getString(1);
				sd = new SourceData(srcIp);
				
				latLongQuery = new DimesQuery(QueryType.LatLongQuery, "DIMES_PLAYGROUND", srcIp);
				
//				String secQueryHard = "SELECT latitude, longitude FROM DIMES_PLAYGROUND.IPsTblFull "
//									+ "WHERE (DIMES_PLAYGROUND.IPsTblFull.IP = '"+srcIp+"');";
				secQuery = latLongQuery.toString();
				
				secRs = secondConnector.submitStatement(secQuery);
				
				if ((secRs != null) && (secRs.next()))
				{
					sd.setSourceLatitude(secRs.getDouble(1));
					sd.setSourceLongitude(secRs.getDouble(2));
				}
				else
				{
					retVal = "Error in main: Second result-set is empty for source";
					System.out.println(retVal);
					return retVal;
				}
			}
			else
			{
				retVal = "Error in main: Result-Set is empty";
				System.out.println(retVal);
				return retVal;
			}
			
			while ((rs != null) && (rsNotEmpty))
//			long[] seqNums = {14139275, 14139277, 14139289, 14139291, 14139317,
//							  15128682, 15128696, 15128702, 15167885, 15167887};
//			String[] destIps = {"217.12.208.2", "202.55.80.1", "203.14.32.1", "202.176.208.1", "65.122.92.2",
//								"194.8.5.2", "153.96.12.1", "66.195.7.1", "212.150.32.1", "66.38.255.1"};
//			long[] avgTimes = {114, 373, 329, 304, 143,
//							    79,  14, 121, 104, 210};
			//for (int i = 0; i < sd.getNumOfTargets(); i++)
			//for (int i = 0; i < 10; i++)
			{
				td		= new TargetData();
				srcIp   = rs.getString(1);
				seqNum  = rs.getLong(2);
				dstIp   = rs.getString(3);
				avgTime = rs.getLong(4);
				
				if (null == latLongQuery)
				{
					latLongQuery = new DimesQuery(QueryType.LatLongQuery, "DIMES_PLAYGROUND", dstIp);
				}
				else
				{
					latLongQuery.setIp(dstIp);
				}
				secQuery = latLongQuery.toString();
				
				secRs = secondConnector.submitStatement(secQuery);
				
				//System.out.print(srcIp + "\t" + seqNum + "\t" + dstIp + "\t" + avgTime);
				td.setTargetIP(dstIp);
				td.setMeasuredTime(avgTime);
				
				if ((secRs != null) && (secRs.next()))
				{
					td.setTargetLatitude(secRs.getDouble(1));
					td.setTargetLongitude(secRs.getDouble(2));
					//System.out.println("\t"+td.getTargetLatitude()+"\t"+td.getTargetLongitude());
				}
				else
				{
					retVal = "Error in main: Second result-set is empty for target: " + dstIp;
					System.out.println(retVal);
					return retVal;
				}
				
				sd.addTarget(seqNum, td);
				
				rsNotEmpty = rs.next();
			}
			/* write data to file */
			DataFileWriter dfw = new DataFileWriter(
					"C:\\javaTimesfile2.txt");
			dfw.writeFullDataToFile(sd, guiDetails.getFirstRadioButton(), guiDetails.getSecondRadioButton());
			dfw.closeDataFileWriter();
		}
		catch (SQLException sqlEx)
		{
			// handle any errors
			retVal = "SQLException @ main() \n"
					+"SQLException: " + sqlEx.getMessage() + "\n"
					+"SQLState: " + sqlEx.getSQLState() + "\n"
					+"VendorError: " + sqlEx.getErrorCode();
			System.out.println(retVal);
			sqlEx.printStackTrace();
			return retVal;
		}
		catch (IOException ioEx)
		{
			retVal = "IO error while writing to file:";
			System.out.println(retVal);
			ioEx.printStackTrace();
			return retVal;
		}
		secondConnector.closeConnection();
		
		return retVal;
	}

}
