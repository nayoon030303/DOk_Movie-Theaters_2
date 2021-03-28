package reservation;

public class Reservation {
	private int _key;
	private String userID;
	private int movieareaKey;
	private int price;
	private int seatCount;
	private String seatWhere;
	private String yymmdd;
	private String payHow;
	private String moiveYYMMDD;
	
	public Reservation() {}

	public Reservation(int _key, String userID,int movieareaKey, int price, int seatCount, String seatWhere, String yymmdd,
			String payHow, String movieYYMMDD) {
		super();
		this._key = _key;
		this.userID = userID;
		this.movieareaKey = movieareaKey;
		this.price = price;
		this.seatCount = seatCount;
		this.seatWhere = seatWhere;
		this.yymmdd = yymmdd;
		this.payHow = payHow;
		this.moiveYYMMDD = movieYYMMDD;
	}

	public int get_key() {
		return _key;
	}

	public void set_key(int _key) {
		this._key = _key;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getMovieareaKey() {
		return movieareaKey;
	}

	public void setMovieareaKey(int movieareaKey) {
		this.movieareaKey = movieareaKey;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String getSeatWhere() {
		return seatWhere;
	}

	public void setSeatWhere(String seatWhere) {
		this.seatWhere = seatWhere;
	}

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public String getPayHow() {
		return payHow;
	}

	public void setPayHow(String payHow) {
		this.payHow = payHow;
	}

	public String getMoiveYYMMDD() {
		return moiveYYMMDD;
	}

	public void setMoiveYYMMDD(String moiveYYMMDD) {
		this.moiveYYMMDD = moiveYYMMDD;
	}
	
}
