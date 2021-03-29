package page;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Area.DB_MovieTimeTable;
import Movie.DB_MovieInfo;
import Movie.Movie;
import Movie.MovieTimtTable;
import User.User;
import reservation.DB_reservation;
import reservation.Reservation;
import theater.DB_Theater;

public class MyReservationPage extends CategoryFrame {
	
	//pos	
	private final static int X_LEFT = 200;
	private final static int Y_TOP = 130;
	
	
	//pos
	private final static int POS_X_CENTER = Main.SCREEN_WIDTH/2-50;
	private final static int POS_X_LEFT = Main.SCREEN_WIDTH/2-550;
	private final static int POS_X_RIGHT = Main.SCREEN_WIDTH/2-200+400+50;
	private final static int PaddingTop = 50;
	private static final int MAX_NUMBER = 8;

	//component
	private JPanel panel = new JPanel();
	private JLabel reservation = new JLabel("예매 내역 확인 ·취소");
	private JLabel period = new JLabel("조회 기간 선택");
	private JLabel[][] recent_movieInfo = new JLabel[MAX_NUMBER][6];
	private JLabel record_movieInfo = new JLabel();
	
	
	private Color purple = new Color(82, 12, 139);
	//Font
	private Font font1 = new Font("나눔바른고딕", Font.BOLD, 23); 
	private Font font2 = new Font("휴먼둥근헤드라인", Font.PLAIN, 15);
	private Font font3 = new Font("나눔바른고딕", Font.PLAIN, 25);
	//DB
	private DB_reservation connect_reservation = new DB_reservation();
	private DB_MovieTimeTable connect_movieTimetable = new DB_MovieTimeTable();
	private DB_Theater coneect_theater = new DB_Theater();
	private DB_MovieInfo connect_movie  = new DB_MovieInfo();
	
	private Movie movie;
	private MovieTimtTable movieTimetable;
	
	
	
	//text
	private String[] texts = {"예매일","예매번호","상품명","이용일 / 매수","취소가능일","현재 상태"};
	
	//1080
	private int WIDTH_0 = 130;
	private int WIDTH_1 = 210;
	private int WIDTH_2 = 240;
	private int WIDTH_3 = 200;
	private int WIDTH_4 = 150;
	private int WIDTH_5 = 150;

	private Vector<Reservation> reseravations = new Vector<Reservation>();
	
	public MyReservationPage(User user) {
		
		super("예매 확인 및 취소");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);//창크기 조정x
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);//레이아웃 null
		setVisible(true);	
		
		
		reseravations = connect_reservation.getReservations(user.getUserID());
		
		//Panel
		panel.setBackground(Color.WHITE);
		panel.setBounds(0,(int) (Main.SCREEN_HEIGHT*0.25),Main.SCREEN_WIDTH,(int)(Main.SCREEN_HEIGHT*0.75));
		panel.setLayout(null);
		add(panel);

		//예약 확인 및 취소
		reservation.setBounds(200,50,400,50);
		reservation.setOpaque(false);
		reservation.setFont(font1);
		reservation.setBackground(Color.RED);
		panel.add(reservation);
		
		
		//조회기간선택
		period.setBounds(700,50,400,50);
		period.setOpaque(false);
		period.setFont(font1);
		period.setBackground(Color.RED);
		panel.add(period);
		
		if(reseravations.size()>0) {
			int num = reseravations.size();
			for(int y=0; y<MAX_NUMBER; y++) {
				for(int x=0; x<recent_movieInfo[y].length; x++){
					//기본 설정
					recent_movieInfo[y][x] = new JLabel("aa");
					recent_movieInfo[y][x].setBorder(new LineBorder(purple, 1));
					recent_movieInfo[y][x].setBorder(new LineBorder(purple, 1));
					recent_movieInfo[y][x].setFont(font2);
					recent_movieInfo[y][x].setBackground(Color.red);
					recent_movieInfo[y][x].setHorizontalAlignment(JLabel.CENTER);
					//recent_movieInfo[y][x].setBounds(POS_X_CENTER - 50+(x*160), PaddingTop+300+(250/4)*(y+1)+3, 150, 250/4);
				    panel.add(recent_movieInfo[y][x]);
					
				    //상단
				    if(y == 0)
				    {
				    	recent_movieInfo[y][x].setText(texts[x]);
				    }
				    if(x == 0 )
				    {
				    	recent_movieInfo[y][x].setBounds(X_LEFT, Y_TOP+(y)*60, WIDTH_0, 60);
				    }else if(x == 1)
				    {
				    	recent_movieInfo[y][x].setBounds(X_LEFT +WIDTH_0, Y_TOP+(y)*60, WIDTH_1, 60);
				    }else if(x == 2)
				    {
				    	recent_movieInfo[y][x].setBounds(X_LEFT+WIDTH_0+WIDTH_1, Y_TOP+(y)*60, WIDTH_2, 60);
				    }else if(x == 3) 
				    {
				    	recent_movieInfo[y][x].setBounds(X_LEFT+WIDTH_0+WIDTH_1+WIDTH_2, Y_TOP+(y)*60, WIDTH_3, 60);
				    }else if(x == 4)
				    {
				    	recent_movieInfo[y][x].setBounds(X_LEFT+WIDTH_0+WIDTH_1+WIDTH_2+WIDTH_3, Y_TOP+(y)*60, WIDTH_4, 60);
				    }else if(x == 5) 
				    {
				    	recent_movieInfo[y][x].setBounds(X_LEFT+WIDTH_0+WIDTH_1+WIDTH_2+WIDTH_3+WIDTH_4, Y_TOP+(y)*60, WIDTH_5, 60);
				    }
				    	
					
				    if(y!=0 && y<num)
				    {
				    	int number = y-1;
				    	int movieTimetableKey = reseravations.get(number).getMovieTimetable();
					    movieTimetable = connect_movieTimetable.getMovieArea(movieTimetableKey);
					    int movieKey = movieTimetable.getMovieKey();
					    movie = connect_movie.getMovie(movieKey); String yymmdd, movieName,movieYYMMDD,seatCount;
					    //예매일	상품명	이용일/매수		현재상태  
					    yymmdd = reseravations.get(number).getYymmdd();
					    movieName = movie.getM_name();
					    movieYYMMDD = reseravations.get(number).getMoiveYYMMDD();				
					    seatCount =	Integer.toString(reseravations.get(y-1).getSeatCount());
					    
					    if(x==0){
					    	recent_movieInfo[y][x].setText(yymmdd);
					    }else if(x==1) {
					    	recent_movieInfo[y][x].setText(movieName);
					    }else if(x==2) {
					    	recent_movieInfo[y][x].setText(movieYYMMDD);
					    }else if(x==3) {
					    	recent_movieInfo[y][x].setText(movieYYMMDD+"\n"+"aa");
					    }
					}
				}
				
			}
		}else {
			record_movieInfo.setText("아직 최근 예매하신 내역이 없으시네요 !");
		}
		
		
		
	}
}
