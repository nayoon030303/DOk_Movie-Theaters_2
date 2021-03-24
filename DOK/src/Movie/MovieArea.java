package Movie;

public class MovieArea {
	private int _key;
	private int area_key;
	private String hall;
	private int weeks;
	private String startTime;
	private int movieKey;
	private int vacantSeat;
	private String seatState;
	public MovieArea() {}
	public MovieArea(int _key, int area_key, String hall, int weeks, String startTime, int movieKey, int vacantSeat,
			String seatState) {
		super();
		this._key = _key;
		this.area_key = area_key;
		this.hall = hall;
		this.weeks = weeks;
		this.startTime = startTime;
		this.movieKey = movieKey;
		this.vacantSeat = vacantSeat;
		this.seatState = seatState;
	}
	public int get_key() {
		return _key;
	}
	public void set_key(int _key) {
		this._key = _key;
	}
	public int getArea_key() {
		return area_key;
	}
	public void setArea_key(int area_key) {
		this.area_key = area_key;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public int getWeeks() {
		return weeks;
	}
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getMovieKey() {
		return movieKey;
	}
	public void setMovieKey(int movieKey) {
		this.movieKey = movieKey;
	}
	public int getVacantSeat() {
		return vacantSeat;
	}
	public void setVacantSeat(int vacantSeat) {
		this.vacantSeat = vacantSeat;
	}
	public String getSeatState() {
		return seatState;
	}
	public void setSeatState(String seatState) {
		this.seatState = seatState;
	}
	
	
	
}
