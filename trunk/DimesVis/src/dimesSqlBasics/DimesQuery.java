package dimesSqlBasics;

import java.util.Vector;

public class DimesQuery
{

	// class constants
	private static final int		resMainIndex	= 0;
	private static final int		resTraceIndex	= 1;
	private static final int		resTablesMax	= 2;
	private static final int		ipsTblFullIndex	= 0;	

	// class members
	private final QueryType			queryType;
	private String					schema			= null;
	private Vector<String>			tables			= null;
	private String					srcIp			= null;
	private String					destIp			= null;
	private int						dayOfYear		= -1;
	private DimesQueryTimeOption	timeOpt			= null;
	private int						limit			= 0;
	private long					sequenceNum		= 0;
	
	DimesQuery(QueryType queryType, String schemaName, String mainTable,  String tracerouteTable, String sourceIP, int dayOfYear, DimesQueryTimeOption queryTimeOption, int limit)
	{
		this.queryType = queryType;
		
		if (queryType != QueryType.MainQuery)
		{
			System.out.println("DimesQuery: Error, use of wrong QueryType and constructor type.");
			System.out.println("Expected: "+QueryType.MainQuery+" but got: "+queryType);
			return;
		}
		
		this.schema    = schemaName;
		this.tables    = new Vector<String>(resTablesMax);
		this.srcIp     = sourceIP;
		this.dayOfYear = dayOfYear;
		this.timeOpt   = queryTimeOption;
		this.limit     = limit;
		
		this.tables.add(resMainIndex, mainTable);
		this.tables.add(resTraceIndex, tracerouteTable);
	}
	
	DimesQuery(QueryType queryType, String schemaName, String mainTable, String tracerouteTable, String sourceIP, String destIp, int dayOfYear, DimesQueryTimeOption queryTimeOption, int limit)
	{
		this.queryType = queryType;
		
		if (queryType != QueryType.MainQuerySingleIp)
		{
			System.out.println("DimesQuery: Error, use of wrong QueryType and constructor type.");
			System.out.println("Expected: "+QueryType.MainQuerySingleIp+" but got: "+queryType);
			return;
		}
		
		this.schema    = schemaName;
		this.tables    = new Vector<String>(resTablesMax);
		this.srcIp     = sourceIP;
		this.destIp    = destIp;
		this.dayOfYear = dayOfYear;
		this.timeOpt   = queryTimeOption;
		this.limit     = limit;
		
		this.tables.add(resMainIndex, mainTable);
		this.tables.add(resTraceIndex, tracerouteTable);
	}
	
	DimesQuery(QueryType queryType, String schemaName, String ipsTblName, String ip)
	{
		this.queryType = queryType;
		
		if (queryType != QueryType.LatLongQuery)
		{
			System.out.println("DimesQuery: Error, use of wrong QueryType and constructor type.");
			System.out.println("Expected: "+QueryType.LatLongQuery+" but got: "+queryType);
			return;
		}
		
		this.schema = schemaName;
		this.destIp = ip;
		this.tables = new Vector<String>(resTablesMax);

		this.tables.add(ipsTblFullIndex, ipsTblName);
	}
	
	DimesQuery(QueryType queryType, long mainSequenceNumber, String schemaName, String tracerouteTable)
	{
		this.queryType = queryType;
		
		if (queryType != QueryType.TracerouteHopsQuery)
		{
			System.out.println("DimesQuery: Error, use of wrong QueryType and constructor type.");
			System.out.println("Expected: "+QueryType.TracerouteHopsQuery+" but got: "+queryType);
			return;
		}
		
		this.sequenceNum = mainSequenceNumber;
		this.schema      = schemaName;
		this.tables      = new Vector<String>(resTablesMax);

		this.tables.add(resMainIndex, ""); //dummy insert, to make size > 0
		this.tables.add(resTraceIndex, tracerouteTable);
	}
	
	public String toString()
	{
		switch (this.queryType)
		{
			case MainQuery:
				//return noDateToString();
				if (0 < this.dayOfYear)
				{
					return withDateToString();
				}
				else
				{
					return noDateToString();
				}
			
			case MainQuerySingleIp:
				return singleIpToString();
				
			case LatLongQuery:
				return latLongToString();
				
			case TracerouteHopsQuery:
				return traceHopsToString();
			
			default:
				return null;
		}
	}

	private String noDateToString()
	{
		String query = null;
		if ((null == this.schema)	|| (null == this.tables)	||
			(null == this.srcIp)		|| (null == this.timeOpt)	||
			(0    > this.limit))
		{
			return null;
		}
		
		query = "SELECT SourceIP, SequenceNum, DestIP, "+this.timeOpt+" "
			  + "FROM "+this.schema+"."+this.tables.get(resMainIndex)+" "
			  + "LEFT JOIN "+this.schema+"."+this.tables.get(resTraceIndex)+" "
			  + "ON ((" +this.schema+"."+this.tables.get(resMainIndex)+".SequenceNum = "+this.schema+"."+this.tables.get(resTraceIndex)+".MainSequenceNum) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".DestAddress = "+this.schema+"."+this.tables.get(resTraceIndex)+".hopAddress)) "
			  + "WHERE (("+this.schema+"."+this.tables.get(resMainIndex)+".CommandType ='TRACEROUTE') "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".reachedDest = 1) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".SourceIP = '"+this.srcIp+"'))";
		
		if (this.limit != 0)	  
		{
			query += " LIMIT "+this.limit+";";
		}
		else
		{
			query += ";";
		}
		
		return query;
	}
	
	private String withDateToString()
	{
		String query = null;
		if ((null == this.schema)	|| (null == this.tables)	||
			(null == this.srcIp)	|| (null == this.timeOpt)	||
			(0    == this.dayOfYear)|| (0    > this.limit))
		{
			return null;
		}
		
		query = "SELECT SourceIP, SequenceNum, DestIP, "+this.timeOpt+" "
			  + "FROM "+this.schema+"."+this.tables.get(resMainIndex)+" "
			  + "LEFT JOIN "+this.schema+"."+this.tables.get(resTraceIndex)+" "
			  + "ON ((" +this.schema+"."+this.tables.get(resMainIndex)+".SequenceNum = "+this.schema+"."+this.tables.get(resTraceIndex)+".MainSequenceNum) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".DestAddress = "+this.schema+"."+this.tables.get(resTraceIndex)+".hopAddress)) "
			  + "WHERE (("+this.schema+"."+this.tables.get(resMainIndex)+".CommandType ='TRACEROUTE') "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".reachedDest = 1) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".SourceIP = '"+this.srcIp+"') "
			  + "AND (DAYOFYEAR("+this.schema+"."+this.tables.get(resMainIndex)+".InsertTime) = "+this.dayOfYear+"))";
		
		if (this.limit != 0)	  
		{
			query += " LIMIT "+this.limit+";";
		}
		else
		{
			query += ";";
		}
		
		return query;
	}

	private String singleIpToString()
	{
		String query = null;
		if ((null == this.schema)	|| (null == this.tables)	||
			(null == this.srcIp)	|| (null == this.timeOpt)	||
			(null == this.destIp)	|| (0    >= this.limit))
		{
			return null;
		}
		
		query = "SELECT SourceIP, SequenceNum, DestIP, "+this.timeOpt+" "
			  + "FROM "+this.schema+"."+this.tables.get(resMainIndex)+" "
			  + "LEFT JOIN "+this.schema+"."+this.tables.get(resTraceIndex)+" "
			  + "ON (("+this.schema+"."+this.tables.get(resMainIndex)+".DestIP = '"+this.destIp+"') "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".SequenceNum = "+this.schema+"."+this.tables.get(resTraceIndex)+".MainSequenceNum) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".DestAddress = "+this.schema+"."+this.tables.get(resTraceIndex)+".hopAddress)) "
			  + "WHERE (("+this.schema+"."+this.tables.get(resMainIndex)+".CommandType ='TRACEROUTE') "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".reachedDest = 1) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".SourceIP = '"+this.srcIp+"') "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".DestIP = '"+this.destIp+"'))";
		
		if (this.limit != 0)	  
		{
			query += " LIMIT "+this.limit+";";
		}
		else
		{
			query += ";";
		}
		
		return query;
	}
	
	private String latLongToString()
	{
		String query = null;
		if ((null == this.schema) || (null == this.tables) ||
			(null == this.destIp))
		{
			return null;
		}
		query = "SELECT latitude, longitude " +
				"FROM "  +this.schema+"."+this.tables.get(ipsTblFullIndex)+" "
			  + "WHERE ("+this.schema+"."+this.tables.get(ipsTblFullIndex)+".IP = '"+this.destIp+"');";
		
		return query;
	}
	
	private String traceHopsToString()
	{
		if ((null == this.schema)	|| (null == this.tables)	||
			(0 == this.sequenceNum))
		{
			return null;
		}
		
		String query = null;
		
		query = "SELECT sequence, MainSequenceNum, hopAddressStr "
			   +"FROM " +this.schema+"."+this.tables.get(resTraceIndex)+" "
			   +"WHERE "+this.schema+"."+this.tables.get(resTraceIndex)+".MainSequenceNum = "+this.sequenceNum+";";
		return query;
	}

	public void setLatLongIp(String newIp)
	{
		this.destIp = newIp;
	}
	
	public void setDestIp(String newDestIp)
	{
		this.destIp = newDestIp;
	}
}
