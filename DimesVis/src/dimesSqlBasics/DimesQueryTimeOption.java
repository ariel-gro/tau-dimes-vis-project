package dimesSqlBasics;

public enum DimesQueryTimeOption
{
	None(-1), Best(0), Average(1), Worst(2);
	
	private int opt;
	
	DimesQueryTimeOption(int option)
	{
		this.opt = option;
	}
	
	public int getOptionNum()
	{
		return this.opt;
	}
	
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
