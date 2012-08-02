package dimesVisGui;

import java.util.ArrayList;

import dimesSqlBasics.DimesQueryTimeOption;

public class Details {
	
	public static final int addIpRadioOptDontUse = 0;
	public static final int addIpRadioOptAdd     = 1;
	public static final int addIpRadioOptOnlyAdd = 2;
	public static final int excIpRadioOptDontUse = 0;
	public static final int excIpRadioOptUse     = 1;
	
	private int firstViewRadioButtonNumber;
	private String firstViewRadioButtonINFO;
	private int secondViewRadioButtonNumber;
	private String secondViewRadioButtonINFO;
	private int[] sourceIp;
	private DimesQueryTimeOption timeChoiceRadioButtonNumber;
	private int[] date;
	private boolean isDate;
	private String host1Name;
	private String host2Name;
	private int connection1Port;
	private int connection2Port;
	private String user1Name;
	private String user2Name;
	private String password1;
	private String password2;
	private String schema1Name;
	private String schema2Name;
	private String resMainTableName;
	private String resTraceTableName;
	private String ipsTblTableName;
	private int limit;
	private int[][] additionalIp;
	private int additionalIpRadioButton;
	private String additionalIpRadioButtonInfo;
	private int[][] excludeIp;
	private int excludeIpRadioButton;
	private String excludeIpRadioButtonInfo;
	private boolean saveFile;
	private String saveFileName;
	
	
	/*
	 * constructor
	 */
	public Details(){
		firstViewRadioButtonNumber=-1;
		firstViewRadioButtonINFO="";
		secondViewRadioButtonNumber=-1;
		secondViewRadioButtonINFO="";
		sourceIp=new int[4];
		timeChoiceRadioButtonNumber=DimesQueryTimeOption.None;
		date=new int[3];
		isDate=false;
		host1Name="localhost";
		host2Name="localhost";
		connection1Port=-1;
		connection2Port=-1;
		user1Name="codeLimited";
		user2Name="codeLimited";
		password1="";
		password2="";
		schema1Name="";
		schema2Name="";
		resMainTableName="";
		resTraceTableName="";
		ipsTblTableName="";
		limit=-1;
		additionalIpRadioButton=0;
		excludeIpRadioButton=0;
		additionalIpRadioButtonInfo="";
		excludeIpRadioButtonInfo="";
		saveFile=false;
		saveFileName="";
	}
	
	/*
	 * set 1st radio button
	 * @param btnNum
	 * @param info
	 */
	public void setFirstRadioButton(int btnNum, String info){
		firstViewRadioButtonNumber=btnNum;
		firstViewRadioButtonINFO=info;
	}
	
	/*
	 * get 1st radio button
	 * @return firstViewRadioButtonNumber
	 */
	public int getFirstRadioButton(){
		return firstViewRadioButtonNumber;
	}
	
	/*
	 * get 1st radio button info
	 * @return firstViewRadioButtonINFO
	 */
	public String getFirstRadioButtonInfo(){
		return firstViewRadioButtonINFO;
	}
	
	/*
	 * set 2nd radio button
	 * @param btnNum
	 * @param info
	 */
	public void setSecondRadioButton(int btnNum, String info){
		secondViewRadioButtonNumber=btnNum;
		secondViewRadioButtonINFO=info;
	}

	/*
	 * get 2nd radio button
	 * @return secondViewRadioButtonNumber
	 */
	public int getSecondRadioButton(){
		return secondViewRadioButtonNumber;
	}
	
	/*
	 * get 2nd radio button info
	 * @return secondViewRadioButtonINFO
	 */
	public String getSecondRadioButtonInfo(){
		return secondViewRadioButtonINFO;
	}

	/*
	 * set Source Ip
	 * @param first
	 * @param second
	 * @param third
	 * @param fourth
	 */
	public void setSourceIp(int first, int second, int third, int fourth){
		sourceIp[0]=first;
		sourceIp[1]=second;
		sourceIp[2]=third;
		sourceIp[3]=fourth;
	}

	/*
	 * get Source Ip
	 * @return sourceIp[]
	 */
	public int[] getSourceIp(){
		return sourceIp;
	}
	
	/*
	 * set time choice radio button
	 * @param btnNum
	 */
	public void setTimeChoiceRadioButton(int btnNum){
		switch (btnNum)
		{
			case 0:
				timeChoiceRadioButtonNumber=DimesQueryTimeOption.Best;
				break;
			case 1:
				timeChoiceRadioButtonNumber=DimesQueryTimeOption.Average;
				break;
			case 2:
				timeChoiceRadioButtonNumber=DimesQueryTimeOption.Worst;
				break;
				
			default:
				timeChoiceRadioButtonNumber=DimesQueryTimeOption.None;
				break;
		}
	}
	
	/*
	 * get time choice radio button
	 * @return timeChoiceRadioButtonNumber
	 */
	public DimesQueryTimeOption getTimeChoiceRadioButton(){
		return timeChoiceRadioButtonNumber;
	}
	
	/*
	 * set is Date
	 * @param isDate
	 */
	public void setIsDate(boolean isDate){
		this.isDate=isDate;
	}

	/*
	 * get is Date
	 * @return isDate
	 */
	public boolean getIsDate(){
		return isDate;
	}
	
	/*
	 * set Date
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setDate(int day, int month, int year){
		date[0]=day;
		date[1]=month;
		date[2]=year;
	}

	/*
	 * get Date
	 * @return date
	 */
	public int[] getDate(){
		return date;
	}
	
	/*
	 * set first host name
	 * @param hostname
	 */
	public void setFirstHostName(String hostname){
		host1Name=hostname;
	}
	
	/*
	 * get first host name
	 * @return hostname
	 */
	public String getFirstHostName(){
		return host1Name;
	}
	
	/*
	 * set second host name
	 * @param hostname
	 */
	public void setSecondHostName(String hostname){
		host2Name=hostname;
	}
	
	/*
	 * get second host name
	 * @return hostname
	 */
	public String getSecondHostName(){
		return host2Name;
	}
	
	/*
	 * set first Connection Port
	 * @param port
	 */
	public void setFirstConnectionPort(int port){
		connection1Port=port;
	}
	
	/*
	 * get first Connection Port
	 * @return connectionPort
	 */
	public int getFirstConnectionPort(){
		return connection1Port;
	}
	
	/*
	 * set second Connection Port
	 * @param port
	 */
	public void setSecondConnectionPort(int port){
		connection2Port=port;
	}
	
	/*
	 * get second Connection Port
	 * @return connectionPort
	 */
	public int getSecondConnectionPort(){
		return connection2Port;
	}
	
	/*
	 * set first user Name
	 * @param username
	 */
	public void setFirstUserName(String username){
		user1Name=username;
	}
	
	/*
	 * get first user Name
	 * @return userName
	 */
	public String getFirstUserName(){
		return user1Name;
	}
	
	/*
	 * set second user Name
	 * @param username
	 */
	public void setSecondUserName(String username){
		user2Name=username;
	}
	
	/*
	 * get second user Name
	 * @return userName
	 */
	public String getSecondUserName(){
		return user2Name;
	}
	
	/*
	 * set first password
	 * @param password
	 */
	public void setFirstPassword(String password){
		this.password1=password;
	}
	
	/*
	 * get first host name
	 * @return password
	 */
	public String getFirstPassword(){
		return password1;
	}
	
	/*
	 * set second password
	 * @param password
	 */
	public void setSecondPassword(String password){
		this.password2=password;
	}
	
	/*
	 * get second host name
	 * @return password
	 */
	public String getSecondPassword(){
		return password2;
	}
	
	/*
	 * set first schema Name
	 * @param schemaname
	 */
	public void setFirstSchemaName(String schemaname){
		schema1Name=schemaname;
	}
	
	/*
	 * get first schema Name
	 * @return schemaName
	 */
	public String getFirstSchemaName(){
		return schema1Name;
	}
	
	/*
	 * set second schema Name
	 * @param schemaname
	 */
	public void setSecondSchemaName(String schemaname){
		schema2Name=schemaname;
	}
	
	/*
	 * get second schema Name
	 * @return schemaName
	 */
	public String getSecondSchemaName(){
		return schema2Name;
	}
	
	/*
	 * set Table Name
	 * @param tablename
	 */
	public void setResMainTableName(String tablename){
		resMainTableName=tablename;
	}

	/*
	 * get Table Name
	 * @return tableName
	 */
	public String getResMainTableName(){
		return resMainTableName;
	}
	
	/*
	 * set Table Name
	 * @param tablename
	 */
	public void setResTraceTableName(String tablename){
		resTraceTableName=tablename;
	}

	/*
	 * get Table Name
	 * @return tableName
	 */
	public String getResTraceTableName(){
		return resTraceTableName;
	}
	
	/*
	 * set Table Name
	 * @param tablename
	 */
	public void setIpsTblTableName(String tablename){
		ipsTblTableName=tablename;
	}

	/*
	 * get Table Name
	 * @return tableName
	 */
	public String getIpsTblTableName(){
		return ipsTblTableName;
	}
	
	/*
	 * get Limit for number of returned lines
	 * @return tableName
	 */
	public int getLimit()
	{
		return limit;
	}

	/*
	 * set Limit for number of returned lines
	 * @param limit - the limit to set
	 */
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	
	/*
	 * set Additional Ip
	 * @param IpCount
	 * @param ipIndexes
	 */
	public void setAdditionalIp(int IpCount, ArrayList<Integer> ipIndexes){
		additionalIp = new int[IpCount][4];
		for (int i=0; i<IpCount; i++){
			for (int j=0; j<4; j++){
				additionalIp[i][j]=ipIndexes.get(i*4+j);
			}
		}
	}

	/*
	 * get Additional Ip
	 * @return additionalIp[][]
	 */
	public int[][] getAdditionalIp(){
		return additionalIp;
	}
	
	/*
	 * get Additional Ip
	 * @return additionalIp[][]
	 */
	public String getAdditionalIpAsString(){
		String str="";
		for (int i=0; i<additionalIp.length; i++){
			if (i>0) str=str+"\n"; 
			for (int j=0; j<4; j++){
				if (j>0) str=str+".";
				str=str+additionalIp[i][j];
			}
		}
		return str;
	}
	
	/*
	 * set Exclude Ip
	 * @param IpCount
	 * @param ipIndexes
	 */
	public void setExcludeIp(int IpCount, ArrayList<Integer> ipIndexes){
		excludeIp = new int[IpCount][4];
		for (int i=0; i<IpCount; i++){
			for (int j=0; j<4; j++){
				excludeIp[i][j]=ipIndexes.get(i*4+j);
			}
		}
	}

	/*
	 * get Exclude Ip
	 * @return excludeIp[][]
	 */
	public int[][] getExcludeIp(){
		return excludeIp;
	}
	
	/*
	 * get Exclude Ip
	 * @return excludeIp[][]
	 */
	public String getExcludeIpAsString(){
		String str="";
		for (int i=0; i<excludeIp.length; i++){
			if (i>0) str=str+"\n"; 
			for (int j=0; j<4; j++){
				if (j>0) str=str+".";
				str=str+excludeIp[i][j];
			}
		}
		return str;
	}

	/*
	 * set Additional Ip Radio Button
	 * @param btnNum
	 */
	public void setAdditionalIpRadioButton(int btnNum, String info){
		additionalIpRadioButton=btnNum;
		additionalIpRadioButtonInfo=info;
	}

	/*
	 * get Additional Ip Radio Button
	 * @return firstViewRadioButtonNumber
	 */
	public int getAdditionalIpRadioButton(){
		return additionalIpRadioButton;
	}
	
	/*
	 * get info of Additional Ip Radio Button
	 * @return additionalIpRadioButtonInfo
	 */
	public String getAdditionalIpRadioButtonInfo(){
		return additionalIpRadioButtonInfo;
	}

	/*
	 * set Exclude Ip Radio Button
	 * @param btnNum
	 */
	public void setExcludeIpRadioButton(int btnNum, String info){
		excludeIpRadioButton=btnNum;
		excludeIpRadioButtonInfo=info;
	}

	/*
	 * get Exclude Ip Radio Button
	 * @return etAdditionalIpRadioButton
	 */
	public int getExcludeIpRadioButton(){
		return excludeIpRadioButton;
	}
	
	/*
	 * get info of Exclude Ip Radio Button
	 * @return excludeIpRadioButtonInfo
	 */
	public String getExcludeIpRadioButtonInfo(){
		return excludeIpRadioButtonInfo;
	}
	
	public void setIsSaveFile(boolean isSaveFile)
	{
		saveFile=isSaveFile;
	}
	
	public boolean getIsSaveFile()
	{
		return saveFile;
	}
	
	public void setSaveFileName(String saveFileName)
	{
		this.saveFileName=saveFileName;
	}
	
	public String getSaveFileName()
	{
		return this.saveFileName;
	}
}
