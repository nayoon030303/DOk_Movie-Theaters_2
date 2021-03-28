package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Movie.MovieArea;
import User.User;

public class DB_ticket {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private Ticket ticket;
	private Vector<Ticket> tickets = new Vector<Ticket>();
		
	public DB_ticket() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul","root","mirim2");
			st = con.createStatement();
		}catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류:"+e.getMessage());
		}
	}
	
	//데이터 베이스에 ticket정보 추가
	public boolean addTicket(String userID, int movieKey, int price, int seatCount, String seatWhere, String yymmdd,
			String payHow ) {
		try {
			String SQL = "INSERT INTO TICKET (userID, movieareaKey, price, seatCount, seatWhere, yymmdd, payHow) "
					+ "VALUES(\""+userID+"\"," +"\""+ movieKey+"\"," + "\""+price+"\",\"" + seatCount+"\",\""+seatWhere +"\","+ "\""+yymmdd +"\","+ "\""+payHow+"\");";
			//System.out.println(SQL);
			int success = st.executeUpdate(SQL);
				
			//성공
			if(success == 1) {
				return true;
			//실패
			}	
				
		}catch(Exception e) {
			System.out.println("데이터베이스 검색 오류:"+ e.getMessage());
		}
		return false;
	}
	
	public Vector<Ticket>  getTicket(String userID) {
		try {
			tickets.clear();
			String SQL = "select* from ticket where userID like \""+userID+"\" order by yymmdd desc";
			//System.out.println(SQL);
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				ticket = new Ticket();
				ticket.set_key(rs.getInt("_key"));
				ticket.setMovieareaKey(rs.getInt("movieareaKey"));
				ticket.setPrice(rs.getInt("price"));
				ticket.setSeatCount(rs.getInt("seatCount"));
				ticket.setSeatWhere(rs.getString("seatWhere"));
				ticket.setYymmdd(rs.getString("yymmdd"));
				ticket.setPayHow(rs.getString("payHow"));
			
				tickets.add(ticket);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getTicket데이터베이스 검색 오류:" + e.getLocalizedMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				//if(st!=null) {st.close();}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return tickets;
	}
}
