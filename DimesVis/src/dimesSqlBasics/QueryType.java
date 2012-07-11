package dimesSqlBasics;

public enum QueryType
{
	MainQuery, MainQuerySingleIp, LatLongQuery, TracerouteHopsQuery;
	
	public String toString()
	{
		switch (this)
		{
			case MainQuery:
				return "MainQuery";
			
			case MainQuerySingleIp:
				return "MainQuerySingleIp";
				
			case LatLongQuery:
				return "LatLongQuery";
				
			case TracerouteHopsQuery:
				return "TracerouteHopsQuery";
			
			default:
				return "";
		}
	}
}
