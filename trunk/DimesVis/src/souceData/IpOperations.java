package localDataManagement;

public class IpOperations
{

	public static long ipStrToLong(String ipAddrStr)
	{
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
}
