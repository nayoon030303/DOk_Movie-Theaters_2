package page;

import User.User;

public class Main {

	public static final int SCREEN_WIDTH = 1500;
	public static final int SCREEN_HEIGHT = 1000;	
	
	static User user = new User();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new LoadingPage(user);
		// new DOKPage(user);
		new ChartPage(user);
	}

}
