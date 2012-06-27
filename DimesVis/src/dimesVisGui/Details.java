package dimesVisGui;

public class Details {
	private int firstViewRadioButtonNumber;
	private int secondViewRadioButtonNumber;
	private int[] sourceIp;
	private int timeChoiceRadioButtonNumber;
	private int[] date;
	private String hostName;
	private int connectionPort;
	private String userName;
	private String password;
	private String schemaName;
	private String tableName;
	
	
	/*
	 * constructor
	 */
	public Details(){
		firstViewRadioButtonNumber=-1;
		secondViewRadioButtonNumber=-1;
		sourceIp=new int[4];
		timeChoiceRadioButtonNumber=-1;
		date=new int[3];
		hostName="";
		connectionPort=-1;
		userName="";
		password="";
		schemaName="";
		tableName="";
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
		timeChoiceRadioButtonNumber=btnNum;
	}
	
	/*
	 * get time choice radio button
	 * @return timeChoiceRadioButtonNumber
	 */
	public int getTimeChoiceRadioButton(){
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
	 * set host name
	 * @param hostname
	 */
	public void setHostName(String hostname){
		hostName=hostname;
	}
	
	/*
	 * get host name
	 * @return hostname
	 */
	public String getHostName(){
		return hostName;
	}
	
	/*
	 * set Connection Port
	 * @param port
	 */
	public void setConnectionPort(int port){
		connectionPort=port;
	}
	
	/*
	 * get Connection Port
	 * @return connectionPort
	 */
	public int getConnectionPort(){
		return connectionPort;
	}
	
	/*
	 * set user Name
	 * @param username
	 */
	public void setUserName(String username){
		userName=username;
	}
	
	/*
	 * get user Name
	 * @return userName
	 */
	public String getUserName(){
		return userName;
	}
	
	/*
	 * set password
	 * @param password
	 */
	public void setPassword(String password){
		this.password=password;
	}
	
	/*
	 * get host name
	 * @return password
	 */
	public String getPassword(){
		return password;
	}
	
	/*
	 * set schema Name
	 * @param schemaname
	 */
	public void setSchemaName(String schemaname){
		schemaName=schemaname;
	}
	
	/*
	 * get schema Name
	 * @return schemaName
	 */
	public String getSchemaName(){
		return schemaName;
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
}
