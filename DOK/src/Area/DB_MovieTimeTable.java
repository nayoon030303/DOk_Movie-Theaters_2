package Area;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Movie.MovieTimtTable;

public class DB_MovieTimeTable {
	private Connection con;	
	private Statement st;
	private ResultSet rs;
	private MovieTimtTable movieArea;
	private Vector<MovieTimtTable> movieAreas = new Vector<MovieTimtTable>();

	public DB_MovieTimeTable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul", "root",
					"mirim2");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류:" + e.getMessage());
		}
		// movieArea = new MovieArea[countMovieArea()];
	}

	public void makeMovie_Area(int area_key, String hall, int weeks, String startTime, int movieKey, int vacantSeat,String seatState){
		try {
			String SQL = "INSERT INTO movietimetable(movieTTTheaterInfo, movieTTHall , weeks, movieTTStartTime, movieTT_movieKey, movieTTVacantSeat, movieTTSeatState)"
					+ "VALUES(\"" + area_key + "\",\"" + hall + "\",\"" + weeks + "\",\"" + startTime + "\",\""
					+ movieKey + "\",\"" + vacantSeat + "\",\"" + seatState + "\");";
			// System.out.println(SQL);

			int success = st.executeUpdate(SQL);

			if (success != 1) {
				System.out.println("error");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				//if(st!=null) {st.close();}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public void UpdateMovieAreas(int weeks) {
		try {
			String SQL = "UPDATE movietimetable SET movieVacantSeat = 216 , movieSeatState = \"000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000\" "
					+ "where weeks like "+ weeks;
			
			int success = st.executeUpdate(SQL);

			if (success != 1) {
				System.out.println("error");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Vector<MovieTimtTable> getMovieArea(int movie_key, int theater_key){
		try {
			String SQL = "select* from movietimetable where movieTT_movieKey like " + movie_key + " and movieTTTheaterInfo like " + theater_key
					+ " ;";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);

			movieAreas.clear();
			// int n=0;
			while (rs.next()) {

				movieArea = new MovieTimtTable();
				movieArea.set_key(rs.getInt("movieTT_key"));
				movieArea.setArea_key(rs.getInt("movieTTTheaterInfo"));
				movieArea.setHall(rs.getString("movieTTHall"));
				movieArea.setWeeks(rs.getInt("weeks"));
				movieArea.setMovieKey(rs.getInt("movieTT_movieKey"));
				movieArea.setVacantSeat(rs.getInt("movieTTVacantSeat"));
				movieArea.setStartTime(rs.getString("movieTTStartTime"));
				movieArea.setSeatState(rs.getString("movieTTSeatState"));
				movieAreas.add(movieArea);
				// n++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBMovieInfo데이터베이스 검색 오류:" + e.getLocalizedMessage());
		} finally {
			try {
				if(rs!=null) {rs.close();}
				//if(st!=null) {st.close();}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return movieAreas;
	}

	
	public MovieTimtTable getMovieArea(int _key) {
		try {
			movieAreas.clear();
			String SQL = "select* from movietimetable where movieTT_key like " + _key + ";";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				movieArea = new MovieTimtTable();
				movieArea.set_key(rs.getInt("movieTT_key"));
				movieArea.setArea_key(rs.getInt("movieTTTheaterInfo"));
				movieArea.setHall(rs.getString("movieTTHall"));
				movieArea.setWeeks(rs.getInt("weeks"));
				movieArea.setMovieKey(rs.getInt("movieTT_movieKey"));
				movieArea.setVacantSeat(rs.getInt("movieTTVacantSeat"));
				movieArea.setStartTime(rs.getString("movieTTStartTime"));
				movieArea.setSeatState(rs.getString("movieTTSeatState"));
				
		
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBMovieInfo데이터베이스 검색 오류:" + e.getLocalizedMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				//if(st!=null) {st.close();}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return movieArea;
	}
	
	public Vector<MovieTimtTable> getMovieArea(int movie_key, int theater_key, int weeks) {
		try {
			movieAreas.clear();
			String SQL = "select* from movietimetable where movieTT_movieKey like " + movie_key + " and movieTTTheaterInfo like " + theater_key
					+ " and weeks like " + weeks + ";";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				movieArea = new MovieTimtTable();
				movieArea.set_key(rs.getInt("movieTT_key"));
				movieArea.setArea_key(rs.getInt("movieTTTheaterInfo"));
				movieArea.setHall(rs.getString("movieTTHall"));
				movieArea.setWeeks(rs.getInt("weeks"));
				movieArea.setMovieKey(rs.getInt("movieTT_movieKey"));
				movieArea.setVacantSeat(rs.getInt("movieTTVacantSeat"));
				movieArea.setStartTime(rs.getString("movieTTStartTime"));
				movieArea.setSeatState(rs.getString("movieTTSeatState"));
				movieAreas.add(movieArea);
				// n++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBMovieInfo데이터베이스 검색 오류:" + e.getLocalizedMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				//if(st!=null) {st.close();}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return movieAreas;


	}

	public int countMovieArea(){
		int n = 0;
		try {
			String SQL = " SELECT COUNT(*)  FROM movietimetable";
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				n = rs.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(rs!=null) {rs.close();}
				//if(st!=null) {st.close();}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return n;
	}

	public void updateMovieArea(int _key, int vacantSeat, String seatState) {
	
		try {
			String SQL = "UPDATE movietimetable SET movieTTVacantSeat = " + vacantSeat + " , movieTTSeatState = \"" + seatState
					+ "\" WHERE movieTT_key = \"" + _key + "\";";
			// System.out.println(SQL);
			int success = st.executeUpdate(SQL);

			if (success == 1) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				//if(st!=null) {st.close();}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
}
