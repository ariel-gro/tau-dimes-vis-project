package dimesSqlBasics;

import java.util.Vector;

public class DimesQuery
{

	// class constants
	private static final int		resMainIndex	= 0;
	private static final int		resTraceIndex	= 1;

	// class members
	private final QueryType			queryType;
	private String					schema			= null;
	private Vector<String>			tables			= null;
	private String					ip				= null;
	private String					date			= null;
	private DimesQueryTimeOption	timeOpt			= null;
	private int						limit			= 0;
	
	DimesQuery(QueryType queryType, String schemaName, String sourceIP, String date,
			   DimesQueryTimeOption queryTimeOption, int limit)
	{
		this.queryType = queryType;
		
		if (queryType != QueryType.MainQuery)
		{
			System.out.println("DimesQuery: Error, use of wrong QueryType and constructor type.");
			System.out.println("Expected: "+QueryType.MainQuery+" but got: "+queryType);
			return;
		}
		
		this.schema    = schemaName;
		this.tables    = new Vector<String>();
		this.ip     = sourceIP;
		this.date      = date;
		this.timeOpt   = queryTimeOption;
		this.limit     = limit;
		
		this.tables.add(resMainIndex, "raw_res_main_2007");
		this.tables.add(resTraceIndex, "raw_res_tr_2007");
	}
	
	DimesQuery(QueryType queryType, String schemaName, String ip)
	{
		this.queryType = queryType;
		
		if (queryType != QueryType.LatLongQuery)
		{
			System.out.println("DimesQuery: Error, use of wrong QueryType and constructor type.");
			System.out.println("Expected: "+QueryType.LatLongQuery+" but got: "+queryType);
			return;
		}
		
		this.schema = schemaName;
		this.ip = ip;
	}
	
	DimesQuery()
	{
		//TODO: fictional data in this constructor - need to create constructor with arguments
		this.schema    = "dimes_results_2007";
		this.tables    = new Vector<String>();
		this.ip     = "141.35.186.237";
		this.date      = "2007";
		this.timeOpt   = DimesQueryTimeOption.Average;
		this.limit     = 250;
		this.queryType = QueryType.MainQuery;
		
		this.tables.add(resMainIndex, "raw_res_main_2007");
		this.tables.add(resTraceIndex, "raw_res_tr_2007");
	}
	
	public String toString()
	{
		switch (this.queryType)
		{
			case MainQuery:
				return noDateToString();
//				if (null == this.date)
//				{
//					return noDateToString();
//				}
//				else
//				{
//					return withDateToString();
//				}
			
			case LatLongQuery:
				return latLongToString();
			
			default:
				return null;
		}
	}

	private String noDateToString()
	{
		String query = null;
		if ((null == this.schema)	|| (null == this.tables)	||
			(null == this.ip)		|| (null == this.timeOpt)	||
			(0    >= this.limit))
		{
			return null;
		}
		
		query = "SELECT SourceIP, SequenceNum, DestIP, "+this.timeOpt+" "
			  + "FROM "+this.schema+"."+this.tables.get(resMainIndex)+", "
			           +this.schema+"."+this.tables.get(resTraceIndex)+" "
			  + "WHERE (("+this.schema+"."+this.tables.get(resMainIndex)+".reachedDest = 1) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".DestAddress = "+this.schema+"."+this.tables.get(resTraceIndex)+".hopAddress) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".SequenceNum = "+this.schema+"."+this.tables.get(resTraceIndex)+".MainSequenceNum) "
			  + "AND ("+this.schema+"."+this.tables.get(resMainIndex)+".SourceIP = '"+this.ip+"'))";
		
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
		// TODO Auto-generated method stub
		return null;
	}
	
	private String latLongToString()
	{
		String query = null;
		if ((null == this.schema) || (null == this.ip))
		{
			return null;
		}
		query = "SELECT latitude, longitude " +
				"FROM "+this.schema+".IPsTblFull "
			  + "WHERE ("+this.schema+".IPsTblFull.IP = '"+this.ip+"');";
		
		return query;
	}

	public void setIp(String newIp)
	{
		this.ip = newIp;
	}
}
