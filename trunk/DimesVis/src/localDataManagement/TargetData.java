package localDataManagement;

/**
 * This class defines an object that holds data about a single
 * target of an experiment. This is to help ease the use of Map objects in
 * the class SourceData
 */
public class TargetData
{
	private long	targetIP		= 0;
	private double	targetLatitude	= 0;
	private double	targetLongitude	= 0;
	private long	measuredTime	= 0;

	/**
	 * @return the targetIP
	 */
	public long getTargetIpAsLong()
	{
		return targetIP;
	}
	
	/**
	 * @return the targetIP
	 */
	public String getTargetIpAsString()
	{
		return IpOperations.ipLongToStr(targetIP);
	}

	/**
	 * @param targetIP
	 *            the targetIP to set
	 */
	public void setTargetIP(String targetIP)
	{
		this.targetIP = IpOperations.ipStrToLong(targetIP);
	}
	
	/**
	 * @param targetIP
	 *            the targetIP to set
	 */
	public void setTargetIP(long targetIP)
	{
		this.targetIP = targetIP;
	}

	/**
	 * @return the targetLatitude
	 */
	public double getTargetLatitude()
	{
		return targetLatitude;
	}

	/**
	 * @param targetLatitude
	 *            the targetLatitude to set
	 */
	public void setTargetLatitude(double targetLatitude)
	{
		this.targetLatitude = targetLatitude;
	}

	/**
	 * @return the targetLongatude
	 */
	public double getTargetLongitude()
	{
		return targetLongitude;
	}

	/**
	 * @param targetLongitude
	 *            the targetLongatude to set
	 */
	public void setTargetLongitude(double targetLongitude)
	{
		this.targetLongitude = targetLongitude;
	}

	/**
	 * @return the measuredTime
	 */
	public long getMeasuredTime()
	{
		return measuredTime;
	}

	/**
	 * @param measuredTime
	 *            the measuredTime to set
	 */
	public void setMeasuredTime(long measuredTime)
	{
		this.measuredTime = measuredTime;
	}
}