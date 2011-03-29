package dimesSqlBasics;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingThings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String query = "SELECT City, IP, Hostname, ASNumber "
				+ "FROM DIMES_PLAYGROUND.IPsTblFull "
				+ "WHERE Country=\"Israel\" " + "ORDER BY City " + "LIMIT 10;";

		Connector connector = new Connector();
		connector.connect();
		ResultSet rs = connector.submitStatement(query);
		try {
			System.out.println("City\t\tIP\tHostname\tASnumber");
			System.out.println("-----------------------------------");
			while(rs.next()){
				for (int i=1; i<=4; i++){
					System.out.print(rs.getString(i) + "\t");
					if (i==1){
						System.out.print("\t");
					}
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connector.closeConnection();
	}

}
