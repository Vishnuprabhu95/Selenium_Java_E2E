package academy.commonComponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;

import com.mysql.cj.xdevapi.Statement;

public class DBOperations {

	public synchronized HashMap<String, String> getSqlResultInMap(String query) {
		HashMap<String, String> data_map = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(null);
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData();
			
			data_map = new HashMap<String, String>();
			while(rs.next()) {
				for(int i=1; i<= md.getColumnCount(); i++) {
					data_map.put(md.getColumnName(i), rs.getString(i));
				}
			}
			System.out.println("data_map==>" + data_map);
			con.close();
			
		}catch(Exception e) {
			
		}
		return data_map;
	}
	
	
}
