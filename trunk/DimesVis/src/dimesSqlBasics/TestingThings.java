package dimesSqlBasics;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import localDataManagement.DataFileWriter;
import localDataManagement.SourceData;
import localDataManagement.TargetData;


public class TestingThings
{

	public static String now()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		/*
		 * String query = "SELECT City, IP, Hostname, ASNumber " +
		 * "FROM DIMES_PLAYGROUND.IPsTblFull " + "WHERE Country=\"Israel\" " +
		 * "ORDER BY City " + "LIMIT 10;";
		 */
		
		String mainQuery =
				  "SELECT SourceIP, SequenceNum, DestIP, avgTime "
				+ "FROM DIMES_PLAYGROUND.raw_res_main_2007_21, DIMES_PLAYGROUND.raw_res_traceroute_2007_21 "
				+ "WHERE ((DIMES_PLAYGROUND.raw_res_main_2007_21.reachedDest = 1) "
				+ "AND (DIMES_PLAYGROUND.raw_res_main_2007_21.DestAddress = DIMES_PLAYGROUND.raw_res_traceroute_2007_21.hopAddress) "
				+ "AND (DIMES_PLAYGROUND.raw_res_main_2007_21.SequenceNum = DIMES_PLAYGROUND.raw_res_traceroute_2007_21.MainSequenceNum) "
				+ "AND (INET_ATON(DIMES_PLAYGROUND.raw_res_main_2007_21.SourceIP) = INET_ATON('141.35.186.237')));";

		/*
		 * Connector connector = new Connector(5551, "codeLimited", "",
		 * "DIMES_PLAYGROUND");
		 */
		//Connector connector = new Connector(5563, "codeLimited", "", "DIMES_PLAYGROUND");
		//connector.connect();
		
		Connector connector2 = new Connector(5551, "codeLimited", "", "DIMES_PLAYGROUND");
		connector2.connect();

		//System.out.println("Submit Statement Started at: " + now());
		//ResultSet rs = connector.submitStatement(mainQuery);
		//System.out.println("Submit Statement Ended at: " + now());
		try
		{
			/* System.out.println("City\t\tIP\tHostname\tASnumber"); */
			System.out.println("SrcIP\tSequence#\tDestIP\tAvgTime\tDestLat\tDestLong");
			System.out.println("-----------------------------------");
			
			String		srcIp = null, dstIp = null;
			long		seqNum = 0, avgTime = 0;
			SourceData	sd	= null;
			TargetData	td	= null;
			boolean		rsNotEmpty = true;//rs.next();
			ResultSet	secRs;
			String		secQuery;
			
			if (rsNotEmpty)
			{
				srcIp = "141.35.186.237";//rs.getString(1);
				sd = new SourceData(srcIp);
				
				secQuery = "SELECT latitude, longitude FROM DIMES_PLAYGROUND.IPsTblFull "
						 + "WHERE (DIMES_PLAYGROUND.IPsTblFull.IP = '"+srcIp+"');";
				secRs = connector2.submitStatement(secQuery);
				
				if ((secRs != null) && (secRs.next()))
				{
					sd.setSourceLatitude(secRs.getDouble(1));
					sd.setSourceLongitude(secRs.getDouble(2));
				}
				else
				{
					System.out.println("Error in main: Second result-set is empty for source");
				}
			}
			else
			{
				System.out.println("Error in main: Result-Set is empty");
			}
			
			//while ((rs != null) && (rsNotEmpty))
			long[] seqNums = {14139275, 14139277, 14139289, 14139291, 14139317,
							  15128682, 15128696, 15128702, 15167885, 15167887};
			String[] destIps = {"217.12.208.2", "202.55.80.1", "203.14.32.1", "202.176.208.1", "65.122.92.2",
								"194.8.5.2", "153.96.12.1", "66.195.7.1", "212.150.32.1", "66.38.255.1"};
			long[] avgTimes = {114, 373, 329, 304, 143,
							    79,  14, 121, 104, 210};
			//for (int i = 0; i < sd.getNumOfTargets(); i++)
			for (int i = 0; i < 10; i++)
			{
				td		= new TargetData();
				srcIp   = "141.35.186.237";//rs.getString(1);
				seqNum  = seqNums[i];//rs.getLong(2);
				dstIp   = destIps[i];//rs.getString(3);
				avgTime = avgTimes[i];//rs.getLong(4);
				
				secQuery = "SELECT latitude, longitude FROM DIMES_PLAYGROUND.IPsTblFull "
					     + "WHERE (DIMES_PLAYGROUND.IPsTblFull.IP = '"+dstIp+"');";
				
				secRs = connector2.submitStatement(secQuery);
				
				System.out.print(srcIp + "\t" + seqNum + "\t" + dstIp + "\t" + avgTime);
				td.setTargetIP(dstIp);
				td.setMeasuredTime(avgTime);
				
				if ((secRs != null) && (secRs.next()))
				{
					td.setTargetLatitude(secRs.getDouble(1));
					td.setTargetLongitude(secRs.getDouble(2));
					System.out.println("\t"+td.getTargetLatitude()+"\t"+td.getTargetLongitude());
				}
				else
				{
					System.out.println("Error in main: Second result-set is empty for target: " + dstIp);
				}
				
				sd.addTarget(seqNum, td);
				
				//rsNotEmpty = rs.next();
			}
			/* write data to file */
			DataFileWriter dfw = new DataFileWriter(
					"C:\\Documents and Settings\\Ariel\\Desktop\\Ariel\\eclipse-rcp-helios-SR2-win32\\workspace\\javaTimesfile.txt");
			dfw.writeFullDataToFile(sd);
			dfw.closeDataFileWriter();
		}
		catch (SQLException ex)
		{
			// handle any errors
			System.out.println("SQLException @ main()");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			// ex.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("IO error while writing to file:");
			e.printStackTrace();
		}
		connector2.closeConnection();
	}

}
