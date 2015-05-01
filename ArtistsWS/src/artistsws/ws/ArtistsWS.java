package artistsws.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.dbcp.BasicDataSource;

import com.mysql.jdbc.Statement;


public class ArtistsWS {

	static String dburl;
	static String username;
	static String password;
	static int maxIdle;
	
	private BasicDataSource dataSource;
	
	static {
		//try {
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			ResourceBundle rb = ResourceBundle.getBundle("artistsws.ws.DbProperties");
			
			dburl = rb.getString("dburl");
			username = rb.getString("username");
			password = rb.getString("password");
			maxIdle = Integer.parseInt(rb.getString("maxIdle"));
			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public ArtistsWS() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setMaxIdle(maxIdle);
		dataSource.setUrl(dburl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
	}

	private Connection getConnection() throws SQLException{
		//return DriverManager.getConnection(dburl,username, password);
		return dataSource.getConnection();
	}
	
	public boolean addArtist(String artist){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			
			
			conn = getConnection();
			
			pstmt = conn.prepareStatement("INSERT INTO artist VALUES (?)");
		
			pstmt.setString(1, artist);
			
			pstmt.execute();
			
			result = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return result;
		
	}

	public String[] findAllArtists(){
		
		ResultSet rs = null;
		PreparedStatement stm = null;
		Connection conn = null;
		
		try{
			List<String> arts = new LinkedList<String>();
			
			conn = getConnection();
			
			stm = conn.prepareStatement("SELECT * FROM artist");
			
			rs = stm.executeQuery();
			
			while(rs.next()){
				arts.add(rs.getString(1));
			}

			String[] ret = new String[arts.size()];
			int i = 0;
			for(String a : arts){
				ret[i++] = a;
			}
			return ret;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stm != null){
				try {
					stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return new String[0];
	}
	
}
