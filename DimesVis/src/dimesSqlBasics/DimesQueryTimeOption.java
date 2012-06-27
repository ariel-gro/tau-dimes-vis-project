package dimesSqlBasics;

public enum DimesQueryTimeOption
{
	Best, Average, Worst; 
	
	public String toString()
	{
		String retVal = null;
		
		switch(this)
		{
			case Best:
				retVal = "bestTime";
				break;
				
			case Average:
				retVal = "avgTime";
				break;
				
			case Worst:
				retVal = "worstTime";
				break;
				
			default:
				retVal = null;
		}
		
		return retVal;
	}
}
