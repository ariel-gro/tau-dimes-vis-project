/*
 * This class defines and implements the functions for the
 * SourceData object. This object is used to hold on to data we
 * read from DIMES' data-base about a specific source of
 * experiments (source of traceroute experiments)
 * For DIMES Visualization Project
 * 
 * Amir Lazevnik and Ariel Groenteman
 * Tel-Aviv University
 * 2012
 */
package localDataManagement;

import java.util.HashMap;

public class SourceData implements SourceDataInterface
{

	private long	sourceIP		= 0;
	private double	sourceLongitude	= 0;
	private double	sourceLatitude	= 0;
	private boolean	isPrivateIP		= false;
	private long	realSourceIP	= 0;
	/*
	 * In this HashMap we store a local list of all targets this source
	 * performed a traceroute experiment to
	 */
	private HashMap<Long, TargetData>	targetsDataMap	= null;

	public SourceData(String srcIpAddr)
	{
		this.sourceIP = IpOperations.ipStrToLong(srcIpAddr);
		this.isPrivateIP = IpOperations.isPrivateIp(srcIpAddr);
		this.targetsDataMap = new HashMap<Long, TargetData>();
	}

	public double getSourceLongitude()
	{
		return sourceLongitude;
	}

	public void setSourceLongitude(double sourceLongitude)
	{
		this.sourceLongitude = sourceLongitude;
	}

	public double getSourceLatitude()
	{
		return sourceLatitude;
	}

	public void setSourceLatitude(double sourceLatitude)
	{
		this.sourceLatitude = sourceLatitude;
	}

	public long getSourceIpAsLong()
	{
		return sourceIP;
	}

	public String getSourceIpAsString()
	{
		return IpOperations.ipLongToStr(sourceIP);
	}

	public void addTarget(long seqNum, TargetData target)
	{
		if (this.targetsDataMap.containsKey(seqNum))
		{
			TargetData existingTarget = this.targetsDataMap.get(seqNum);
			if (existingTarget.getTargetIpAsLong() == target.getTargetIpAsLong())
			{
				//do nothing!!
				//this is a known issue (artifact) in traceroute,
				//so we ignore the repeated target and stay with the
				//first one we got, which is the first one showing
				//in the trace hops.
			}
			else
			{
				//we have the same sequence number for different IPs.
				//this is an error!!
				System.out.println("Error in addTarget: sequence number " + seqNum + " already exists");
			}
		}
		else
		{
			this.targetsDataMap.put(seqNum, target);
		}
	}
	
	public Long[] getAllSequenceNumbers()
	{
		Long[] retArr = new Long[this.targetsDataMap.size()];
		this.targetsDataMap.keySet().toArray(retArr);
		
		return retArr;
	}
	
	public TargetData getTarget(long sequenceNum)
	{
		return this.targetsDataMap.get(sequenceNum);
	}
	
	public int getNumOfTargets()
	{
		return this.targetsDataMap.size();
	}
	
	public boolean isPrivateIpAddress()
	{
		return this.isPrivateIP;
	}
	
	public void setRealSourceIP(String realIp)
	{
		this.realSourceIP = IpOperations.ipStrToLong(realIp);
	}
	
	public void setRealSourceIP(long realIp)
	{
		this.realSourceIP = realIp;
	}
	
	public long getRealSourceIpAsLong()
	{
		if (this.isPrivateIP)
		{
			return this.realSourceIP;
		}
		else
		{
			return 0;
		}
	}

	public String getRealSourceIpAsString()
	{
		if (this.isPrivateIP)
		{
			return IpOperations.ipLongToStr(this.realSourceIP);
		}
		else
		{
			return null;
		}
	}
}
