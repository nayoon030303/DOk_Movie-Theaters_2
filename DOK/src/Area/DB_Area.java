package Area;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB_Area {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private Area[] area;
	
	
	public DB_Area() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul","root","mirim2");
			st = con.createStatement();
		}catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류:"+e.getMessage());
		}
		area = new Area[countArea()];
	}
	
	public Area[] getArea() {
		try{
			String SQL = "SELECT* FROM area";
			
			rs = st.executeQuery(SQL);
			
			int n = 0;
			while(rs.next()) {
				area[n] = new Area();
				area[n].setArea(rs.getString("area_key"));
				n++;
			}
			 
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return area;
	}
	
	public int countArea() {
		int n = 0;
		try {
			String SQL = " SELECT COUNT(*)  FROM area";
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
