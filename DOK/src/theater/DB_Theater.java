package theater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Movie.Movie;

public class DB_Theater {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private Theater[] theaters;
	private Theater theater;
	
	
	public DB_Theater() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul","root","mirim2");
			st = con.createStatement();
		}catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류:"+e.getMessage());
		}
		theaters = new Theater[countTheater()];
		//System.out.println(countTheater());
	}
	
	
	
	public boolean makeMovie_Theater(String area, String country, int numHall) {
		try{
			String SQL = "INSERT INTO theaterinfo(area,country, numHall)"
					+"VALUES(\""+area+"\",\""+country+"\","+numHall+");";
			//System.out.println(SQL);
			int success = st.executeUpdate(SQL);
			
			if(success == 1) { return true; }
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
	
	public Theater[] getTheater() {
		try {
			String SQL = "SELECT* FROM theaterinfo";
			rs = st.executeQuery(SQL);
			int n = 0;
			while(rs.next()) {
				theaters[n] = new Theater();
				theaters[n].set_key(rs.getInt("_key"));
				theaters[n].setArea(rs.getString("area"));
				theaters[n].setCountry(rs.getString("country"));
				theaters[n].setNumHall(rs.getInt("numHall"));
				
				n++;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArea데이터베이스 검색 오류:"+ e.getLocalizedMessage());
		}
		return theaters;
		
	}
	
	public Theater getTheater(int key) {
		try {
			String SQL = "SELECT* FROM theaterinfo where _key like "+key;
			rs = st.executeQuery(SQL);
			int n = 0;
			while(rs.next()) {
				theater = new Theater();
				theater.setArea(rs.getString("area"));
				theater.setCountry(rs.getString("country"));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArea데이터베이스 검색 오류:"+ e.getLocalizedMessage());
		}
		return theater;
		
	}
	
	public Theater[] getTheater(String area) {
		try {
			String SQL = "SELECT* FROM theaterinfo where area like \""+area+"\";";
			//System.out.println(SQL);
			rs = st.executeQuery(SQL);
			int n = 0;
			while(rs.next()) {
				theaters[n] = new Theater();
				theaters[n].set_key(rs.getInt("_key"));
				theaters[n].setArea(rs.getString("area"));
				theaters[n].setCountry(rs.getString("country"));
				theaters[n].setNumHall(rs.getInt("numHall"));
				
				n++;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArea데이터베이스 검색 오류:"+ e.getLocalizedMessage());
		}
		return theaters;
		
	}
	
	public int countTheater() {
		int n = 0;
		try {
			String SQL = " SELECT COUNT(*)  FROM theaterinfo";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				n = rs.getInt("COUNT(*)");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return n;
	}
	
}
