package theater;

public class Theater {	
	
	
	
	private int _key;
	private String area;
	private String country;
	private int numHall;
	
	public Theater() {}
	public Theater(int _key, String area, String countrh, int numHall) {
		super();
		this._key = _key;
		this.area = area;
		this.country = countrh;
		this.numHall = numHall;
	}
	public int get_key() {
		return _key;
	}
	public void set_key(int _key) {
		this._key = _key;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getNumHall() {
		return numHall;
	}
	public void setNumHall(int numHall) {
		this.numHall = numHall;
	}
	
	
}
