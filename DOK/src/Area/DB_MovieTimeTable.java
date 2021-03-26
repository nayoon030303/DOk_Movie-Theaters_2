package Area;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Movie.MovieArea;

public class DB_MovieTimeTable {
	private Connection con;	
	private Statement st;
	private ResultSet rs;
	private MovieArea movieArea;
	private Vector<MovieArea> movieAreas = new Vector<MovieArea>();

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
			String SQL = "INSERT INTO moviearea(area_key, hall , weeks, startTime, movieKey, vacantSeat, seatState)"
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
			String SQL = "UPDATE moviearea SET vacantSeat = 216 , seatState = \"000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000/000000000000000000000000\" "
					+ "where weeks like "+ weeks;
			
			int success = st.executeUpdate(SQL);

			if (success != 1) {
				System.out.println("error");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Vector<MovieArea> getMovieArea(int movie_key, int theater_key){
		try {
			String SQL = "select* from moviearea where movieKey like " + movie_key + " and area_key like " + theater_key
					+ " ;";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);

			movieAreas.clear();
			// int n=0;
			while (rs.next()) {

				movieArea = new MovieArea();
				movieArea.set_key(rs.getInt("_key"));
				movieArea.setArea_key(rs.getInt("area_key"));
				movieArea.setHall(rs.getString("hall"));
				movieArea.setWeeks(rs.getInt("weeks"));
				movieArea.setMovieKey(rs.getInt("movieKey"));
				movieArea.setVacantSeat(rs.getInt("vacantSeat"));
				movieArea.setStartTime(rs.getString("startTime"));
				movieArea.setSeatState(rs.getString("seatState"));
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

	
	public MovieArea getMovieArea(int _key) {
		try {
			movieAreas.clear();
			String SQL = "select* from moviearea where _key like " + _key + ";";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				movieArea = new MovieArea();
				movieArea.set_key(rs.getInt("_key"));
				movieArea.setArea_key(rs.getInt("area_key"));
				movieArea.setHall(rs.getString("hall"));
				movieArea.setWeeks(rs.getInt("weeks"));
				movieArea.setMovieKey(rs.getInt("movieKey"));
				movieArea.setVacantSeat(rs.getInt("vacantSeat"));
				movieArea.setStartTime(rs.getString("startTime"));
				movieArea.setSeatState(rs.getString("seatState"));

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
	
	public Vector<MovieArea> getMovieArea(int movie_key, int theater_key, int weeks) {
		try {
			movieAreas.clear();
			String SQL = "select* from moviearea where movieKey like " + movie_key + " and area_key like " + theater_key
					+ " and weeks like " + weeks + ";";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				movieArea = new MovieArea();
				movieArea.set_key(rs.getInt("_key"));
				movieArea.setArea_key(rs.getInt("area_key"));
				movieArea.setHall(rs.getString("hall"));
				movieArea.setWeeks(rs.getInt("weeks"));
				movieArea.setMovieKey(rs.getInt("movieKey"));
				movieArea.setVacantSeat(rs.getInt("vacantSeat"));
				movieArea.setStartTime(rs.getString("startTime"));
				movieArea.setSeatState(rs.getString("seatState"));
				movieAreas.add(movieArea);
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
			String SQL = " SELECT COUNT(*)  FROM movieArea";
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
			String SQL = "UPDATE movieArea SET vacantSeat = " + vacantSeat + " , seatState = \"" + seatState
					+ "\" WHERE _key = \"" + _key + "\";";
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
