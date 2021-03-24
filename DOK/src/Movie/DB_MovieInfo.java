package Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB_MovieInfo {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	// private final static int MOVIE_NUM = 70;
	private Movie[] movies; // 백터로 바꾸기
	private Movie movie;

	public DB_MovieInfo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul", "root",
					"mirim2");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류:" + e.getMessage());
		}
		movies = new Movie[countMovie()];
	}

	public Movie[] getMovieInfoAll(String calum) {

		try {
			String SQL = "select* from movie order by " + calum + " desc";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);
			int n = 0;
			while (rs.next()) {
				/*
				 * System.out.println(rs.getString("m_name"));
				 * System.out.println(rs.getString("genre"));
				 * System.out.println(rs.getString("open_day"));
				 * System.out.println(rs.getInt("audience"));
				 * System.out.println(rs.getDouble("rating"));
				 */

				movies[n] = new Movie();
				movies[n].set_key(rs.getInt("_key"));
				movies[n].setM_name(rs.getString("m_name"));
				movies[n].setGenre(rs.getString("genre"));
				movies[n].setOpen_day(rs.getString("open_day"));
				movies[n].setAudience(rs.getInt("audience"));
				movies[n].setRating(rs.getDouble("rating"));
				movies[n].setGrade(rs.getString("grade"));
				movies[n].setCountry(rs.getString("country"));
				movies[n].setRunning_time(rs.getInt("running_time"));
				movies[n].setDisributor(rs.getString("distributor"));
				n++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBMovieInfo데이터베이스 검색 오류:" + e.getLocalizedMessage());
		}
		return movies;
	}

	public Movie getMovie(int key) {

		try {
			String SQL = "select* from movie where  _key like "+key;
			//System.out.println(SQL);
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				
				movie = new Movie();
				movie.setM_name(rs.getString("m_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBMovieInfo데이터베이스 검색 오류:" + e.getLocalizedMessage());
		}
		return movie;
	}

	public Movie[] getMovieInfo(String genr, String calum) {

		try {
			String SQL = "select* from movie where genre like '" + genr + "' order by " + calum + " desc";
			// System.out.println(SQL);
			rs = st.executeQuery(SQL);
			int n = 0;
			while (rs.next()) {
				/*
				 * System.out.println(rs.getString("m_name"));
				 * System.out.println(rs.getString("genre"));
				 * System.out.println(rs.getString("open_day"));
				 * System.out.println(rs.getInt("audience"));
				 * System.out.println(rs.getDouble("rating"));
				 */

				movies[n] = new Movie();
				movies[n].set_key(rs.getInt("_key"));
				movies[n].setM_name(rs.getString("m_name"));
				movies[n].setGenre(rs.getString("genre"));
				movies[n].setOpen_day(rs.getString("open_day"));
				movies[n].setAudience(rs.getInt("audience"));
				movies[n].setRating(rs.getDouble("rating"));
				movies[n].setGrade(rs.getString("grade"));
				movies[n].setCountry(rs.getString("country"));
				movies[n].setRunning_time(rs.getInt("running_time"));
				movies[n].setDisributor(rs.getString("distributor"));

				n++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBMovieInfo데이터베이스 검색 오류:" + e.getLocalizedMessage());
		}
		return movies;
	}

	public int countMovie() {
		int n = 0;
		try {
			String SQL = " SELECT COUNT(*)  FROM movie";
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				n = rs.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n;
	}
}
