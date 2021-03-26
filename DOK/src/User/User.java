package User;


public class User {
	private String userID, userPassword, userName, userYYMMDD, userGender, userPhone, userTaste1;
	private boolean userIsLogin;
	private String userProfile;
	public User() {}
	//8°¡Áö
	public User(String userID,String userPassword, String userName, String useryymmdd, String userGender, String userPhone, String userTaste1,  boolean isLogin) {
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userYYMMDD = useryymmdd;
		this.userGender = userGender;
		this.userPhone = userPhone;
		this.userTaste1 = userTaste1;
		this.userIsLogin = isLogin;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUseryymmdd() {
		return userYYMMDD;
	}
	public void setUseryymmdd(String useryymmdd) {
		this.userYYMMDD = useryymmdd;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserTaste1() {
		return userTaste1;
	}
	public void setUserTaste1(String userTaste1) {
		this.userTaste1 = userTaste1;
	}
	public boolean getUserIsLogin() {
		return userIsLogin;
	}
	public void setUserIsLogin(boolean isLogin) {
		this.userIsLogin = isLogin;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	

}
