package dimesSqlBasics;

import java.util.Vector;

public class DimesQuery
{

	private String					schema	= null;
	private Vector<String>			tables	= null;
	private String					srcIp	= null;
	private String					date	= null;
	private DimesQueryTimeOption	timeOpt	= null;
	
	DimesQuery()
	{
		//TODO: fictional data in this constructor - need to create constructor with arguments
		this.schema  = "DIMES_PLAYGROUND";
		this.tables  = new Vector<String>();
		this.srcIp   = "141.35.186.237";
		this.date    = null;
		this.timeOpt = DimesQueryTimeOption.Average;
		
		this.tables.add("raw_res_main_2007_21");
		this.tables.add("raw_res_traceroute_2007_21");
	}
	
	public String toString()
	{
		String query = null;
		if ((null == this.schema) || (null == this.tables) ||
			(null == this.srcIp)  || (null == this.date))
		{
			return null;
		}
		
		query = "SELECT SourceIP, SequenceNum, DestIP, avgTime "
			  + "FROM "+this.schema+".raw_res_main_2007_21, "+this.schema+".raw_res_traceroute_2007_21 "
			  + "WHERE (("+this.schema+".raw_res_main_2007_21.reachedDest = 1) "
			  + "AND ("+this.schema+".raw_res_main_2007_21.DestAddress = "+this.schema+".raw_res_traceroute_2007_21.hopAddress) "
			  + "AND ("+this.schema+".raw_res_main_2007_21.SequenceNum = "+this.schema+".raw_res_traceroute_2007_21.MainSequenceNum) "
			  + "AND ("+this.schema+".raw_res_main_2007_21.SourceIP = '"+this.srcIp+"'));";
		
		return query;
	}
}
