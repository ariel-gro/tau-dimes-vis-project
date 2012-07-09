package dimesVisGui;

import dimesSqlBasics.DimesQueryTimeOption;

public class Details {
	private int firstViewRadioButtonNumber;
	private int secondViewRadioButtonNumber;
	private int[] sourceIp;
	private DimesQueryTimeOption timeChoiceRadioButtonNumber;
	private int[] date;
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
	private String tableName;
	private int limit;
	
	
	/*
	 * constructor
	 */
	public Details(){
		firstViewRadioButtonNumber=-1;
		secondViewRadioButtonNumber=-1;
		sourceIp=new int[4];
		timeChoiceRadioButtonNumber=DimesQueryTimeOption.None;
		date=new int[3];
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
		tableName="";
		limit=-1;
	}
	
	/*
	 * set 1st radio button
	 * @param btnNum
	 */
	public void setFirstRadioButton(int btnNum){
		firstViewRadioButtonNumber=btnNum;
	}
	
	/*
	 * get 1st radio button
	 * @return firstViewRadioButtonNumber
	 */
	public int getFirstRadioButton(){
		return firstViewRadioButtonNumber;
	}
	
	/*
	 * set 2nd radio button
	 * @param btnNum
	 */
	public void setSecondRadioButton(int btnNum){
		secondViewRadioButtonNumber=btnNum;
	}

	/*
	 * get 2nd radio button
	 * @return secondViewRadioButtonNumber
	 */
	public int getSecondRadioButton(){
		return secondViewRadioButtonNumber;
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
	public void setTableName(String tablename){
		tableName=tablename;
	}

	/*
	 * get Table Name
	 * @return tableName
	 */
	public String getTableName(){
		return tableName;
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
}
