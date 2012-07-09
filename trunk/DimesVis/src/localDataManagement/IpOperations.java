package localDataManagement;

public class IpOperations
{
	private static final long	classAPrivateIpStart	= ipStrToLong("10.0.0.0");
	private static final long	classAPrivateIpEnd		= ipStrToLong("10.255.255.255");
	private static final long	classBPrivateIpStart	= ipStrToLong("172.16.0.0");
	private static final long	classBPrivateIpEnd		= ipStrToLong("172.31.255.255");
	private static final long	classCPrivateIpStart	= ipStrToLong("192.168.0.0");
	private static final long	classCPrivateIpEnd		= ipStrToLong("192.168.255.255");
	private static final long	APIPAPrivateIpStart		= ipStrToLong("169.254.0.0");
	private static final long	APIPAPrivateIpEnd		= ipStrToLong("169.254.255.255");

	public static long ipStrToLong(String ipAddrStr)
	{
		if ((null == ipAddrStr) || (ipAddrStr.equalsIgnoreCase("null")))
		{
			return 0;
		}
		String[] addrArray = ipAddrStr.split("\\.");
		long retVal = 0;
		int power = 0;

		for (int i = 0; i < addrArray.length; i++)
		{
			power = addrArray.length - 1 - i;
			retVal += ((Integer.parseInt(addrArray[i]) % 256 * Math.pow(256,
					power)));
		}

		return retVal;
	}

	public static String ipLongToStr(long ipAddrInt)
	{
		String retVal = null;

		retVal = ((ipAddrInt >> 24) & 0xFF) + "." + ((ipAddrInt >> 16) & 0xFF)
				+ "." + ((ipAddrInt >> 8) & 0xFF) + "."
				+ ((ipAddrInt >> 0) & 0xFF);

		return retVal;
	}
	
	public static boolean isPrivateIp(String ipAddr)
	{
		boolean retVal = isPrivateIp(ipStrToLong(ipAddr));
		return retVal;
	}
	
	public static boolean isPrivateIp(long ipAddr)
	{
		boolean retVal = false;
		
		if (((ipAddr >= classAPrivateIpStart) && (ipAddr <= classAPrivateIpEnd)) ||
			((ipAddr >= classBPrivateIpStart) && (ipAddr <= classBPrivateIpEnd)) ||
			((ipAddr >= classCPrivateIpStart) && (ipAddr <= classCPrivateIpEnd)) ||
			((ipAddr >= APIPAPrivateIpStart ) && (ipAddr <= APIPAPrivateIpEnd))  ||
			 (ipAddr == 0))
		{
			retVal = true;
		}
		
		return retVal;
	}
}
