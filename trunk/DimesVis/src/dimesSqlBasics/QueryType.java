package dimesSqlBasics;

public enum QueryType
{
	MainQuery, LatLongQuery, TracerouteHopsQuery;
	
	public String toString()
	{
		switch (this)
		{
			case MainQuery:
				return "MainQuery";
				
			case LatLongQuery:
				return "LatLongQuery";
				
			case TracerouteHopsQuery:
				return "TracerouteHopsQuery";
			
			default:
				return "";
		}
	}
}
