package Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import theater.Theater;
import theater.DB_Theater;

public class MakeMovieArea {
	
	public static int THEATER_NUM;
	public static int MOVIE_NUM;
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
				
			
	String[] hallNum;
	String[] hallName = {"1관", "2관"};
	//2,4
	String[][] hall_time = {{"8:00", "10:30", "12:00" ,"13:10","15:10 ","17:30" ,"22:30"},{"22:30","19:40 ","17:30","15:10","13:10","12:00","10:30","8:00"}};
						
	//String[] hall2_time = ;
	/*
	 * String[] hall3_time = ; String[] hall4_time = {"12:10", "16:50 ", "21:40 ",
	 * "23:00 "}; String[] hall5_time = {"12:30 ", "14:40 ", "17:05 ", "19:45 "};
	 */
	
	int seat = 216; //24*9
	String seatState = "000000000000000000000000/"	
			+"000000000000000000000000/"
			+"000000000000000000000000/"
			+"000000000000000000000000/"
			+"000000000000000000000000/"
			+"000000000000000000000000/"
			+"000000000000000000000000/"
			+"000000000000000000000000/"
			+"000000000000000000000000";
	
	int random;
	
	public MakeMovieArea() {
		
		
		DB_MovieInfo movieInfo_connect = new DB_MovieInfo();
		DB_MovieTimeTable movieArea_connect = new DB_MovieTimeTable();
		DB_Theater theater_connect = new DB_Theater();
		
		THEATER_NUM = theater_connect.countTheater();	//극장 수
		MOVIE_NUM = movieInfo_connect.countMovie();	//영화 수
		
		//객체생성
		Movie[] movie = new Movie[MOVIE_NUM];
		Theater[] theterinfo = new Theater[THEATER_NUM];
		
		//정보 불러오기
		movie = movieInfo_connect.getMovieInfoAll("open_day");
		theterinfo = theater_connect.getTheater();
		
		int n=0;
		int count_theater = theater_connect.countTheater();
		int c=0;
		//영화 10개 추가
		System.out.println(count_theater);
		/*for(int k=0; k<count_theater; k++) {
			n=0;
			for(int g =0; g<7; g++) {
				for(int i=0; i<4; i++) {
					for(int j=0; j<2; j++) {
						//System.out.println(theterinfo[k].get_key()+","+hallName[i]+","+hall_time[i%2][j]+","+n+","+g +",m:"+movie[n].get_key());
						//movieArea_connect.makeMovie_Area(theterinfo[k].get_key(),hallName[i],g, hall_time[i%2][j],movie[n].get_key(),seat,seatState);
						c+=1;
					}
				}
				//System.out.println(g);
				
			}
			//System.out.println(k);
		}*/
		int timeNum =0; 
		int count =0; 
		//개봉일이 가까운 영화 14개 상영
		for(int theaterNumber =0; theaterNumber<5; theaterNumber++) {//총 6개의 상영관
			for(int weekNum = 0; weekNum<7; weekNum++) {//7일
				for(int movieNum =0; movieNum<14; movieNum++) {//14개의 영화,시간대
					timeNum%=7;
					System.out.println(theterinfo[theaterNumber].getCountry() +" 영화 :"+movie[movieNum].getM_name()+" 홀:"+hallName[1]+"시간대:"+hall_time[1][timeNum]);
					movieArea_connect.makeMovie_Area(theterinfo[theaterNumber].get_key(),hallName[1],weekNum, hall_time[1][timeNum],movie[movieNum].get_key(),seat,seatState);
					count++;
					timeNum++;
				}
				
			}
			
		}
		System.out.println(count);
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new MakeMovieArea();
	}

}
