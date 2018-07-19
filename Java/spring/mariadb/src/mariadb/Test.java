package mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		String dbid="user1";
		String dbpwd="111111";
		
		String url="jdbc:mysql://192.168.111.100:3306/test";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn=DriverManager.getConnection(url,dbid,dbpwd);
			System.out.println("connection OK");
			pstmt =conn.prepareStatement("SELECT* FROM customer");
			rset=pstmt.executeQuery();
			
			while(rset.next()){
				String id=rset.getString("ID");
				String name=rset.getString("NAME");
				System.out.println(id+" "+name);
			}
			//pstmt.close();
			//rset.close();
			conn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
