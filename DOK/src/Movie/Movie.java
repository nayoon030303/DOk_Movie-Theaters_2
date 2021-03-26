package Movie;

public class Movie {
	
	public static final int ADULT = 10000;
	public static final int TEEN = 8000;
	public static final int KIDS = 5000;
	
	private int _key;
	private String m_name, genre, open_day;
	private int audience;
	private double rating;
	private String grade;
	private String country;
	private int running_time;
	private String disributor;
	public Movie() {}

	public Movie(int _key, String m_name, String genre, String open_day, int audience, double rating) {
		this._key = _key;
		this.m_name = m_name;
		this.genre = genre;
		this.open_day = open_day;
		this.audience = audience;
		this.rating = rating;
	}

	public int get_key() {
		return _key;
	}

	public void set_key(int i) {
		this._key = i;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getOpen_day() {
		return open_day;
	}

	public void setOpen_day(String open_day) {
		this.open_day = open_day;
	}

	public int getAudience() {
		return audience;
	}

	public void setAudience(int audience) {
		this.audience = audience;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getRunning_time() {
		return running_time;
	}

	public void setRunning_time(int running_time) {
		this.running_time = running_time;
	}

	public String getDisributor() {
		return disributor;
	}

	public void setDisributor(String disributor) {
		this.disributor = disributor;
	}
	
}
