package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Movie.MovieTimtTable;
import User.User;

public class DB_reservation {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private Reservation reservation;
	private Vector<Reservation> tickets = new Vector<Reservation>();
		
	public DB_reservation() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul","root","mirim2");
			st = con.createStatement();
		}catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류:"+e.getMessage());
		}
	}
	
	//데이터 베이스에 ticket정보 추가
	public boolean addTicket(Reservation res ) {
		try {
			String SQL = "INSERT INTO reservation (resuserID, res_MovieTimeKey, resPrice, resSeatCount, resSeatWhere, resYYMMDD, resPayHow,resMovieYYMMDD) "
					+ "VALUES(\""+res.getUserID()+"\"," +"\""+ res.getMovieTimetable()+"\"," + "\""+res.getPrice()+"\",\"" 
					+ res.getSeatCount()+"\",\""+res.getSeatWhere() +"\","+ "\""
					+res.getYymmdd() +"\","+ "\""+res.getPayHow()+"\",\""+res.getMoiveYYMMDD()+"\");";
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
	
	public Vector<Reservation>  getReservations(String userID) {
		try {
			tickets.clear();
			String SQL = "select* from reservation where resUserID like \""+userID+"\" order by resYYMMDD DESC";
			//System.out.println(SQL);
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				reservation = new Reservation();
				reservation.set_key(rs.getInt("res_key"));
				reservation.setMovieTimetable(rs.getInt("res_MovieTimeKey"));
				reservation.setPrice(rs.getInt("resPrice"));
				reservation.setSeatCount(rs.getInt("resSeatCount"));
				reservation.setSeatWhere(rs.getString("resSeatWhere"));
				reservation.setYymmdd(rs.getString("resYYMMDD"));
				reservation.setPayHow(rs.getString("resPayHow"));
				reservation.setMoiveYYMMDD(rs.getString("resMovieYYMMDD"));
			
				tickets.add(reservation);
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
