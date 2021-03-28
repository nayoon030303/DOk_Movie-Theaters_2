package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import Area.DB_Area;
import Area.DB_MovieTimeTable;
import Movie.DB_MovieInfo;
import Movie.Movie;
import Movie.MovieTimtTable;
import User.User;
import reservation.DB_reservation;
import reservation.Reservation;
import theater.DB_Theater;
import theater.Theater;


public class MyPage extends CategoryFrame{
	
	//pos
	private final static int POS_X_CENTER = Main.SCREEN_WIDTH/2-50;
	private final static int POS_X_LEFT = Main.SCREEN_WIDTH/2-550;
	private final static int POS_X_RIGHT = Main.SCREEN_WIDTH/2-200+400+50;
	private final static int PaddingTop = 50;

	
	//component
	private JPanel panel = new JPanel();
	private JLabel profile_img = new JLabel();
	private JLabel name = new JLabel();
	private JLabel id = new JLabel("���̵�");
	private JLabel birthday = new JLabel("�������");
	private JLabel phone = new JLabel("��ȭ��ȣ");
	private JLabel taste = new JLabel("��ȭ ����");
	private JButton btn_modify_Info = new JButton();
	private JButton btn_modify_Profile = new JButton();
	private JLabel userInfo = new JLabel("���� ����");
	private JLabel record_movie = new JLabel();
	private JLabel record_movieInfo = new JLabel();
	private JLabel[][] recent_movieInfo = new JLabel[4][4];
	
	private JLabel[] resDay = new JLabel[4];
	private JLabel[] resMovieName = new JLabel[4];
	private JLabel[] resMovieInfo = new JLabel[4];
	private JLabel[] resMovieState = new JLabel[4];
	
	//Design
	private Font font1 = new Font("�޸յձ�������", Font.PLAIN, 25);
	private Font font2 = new Font("�޸յձ�������", Font.PLAIN, 15);
	
	private ImageIcon imgInfo = new ImageIcon("src/imges/info.png");
	private ImageIcon imgRecord = new ImageIcon("src/imges/record.png");
	private ImageIcon imgReInfo = new ImageIcon("src/imges/re_info.png");
	private ImageIcon imgReProfile = new ImageIcon("src/imges/re_profile.png");
	//������ ������
	private ImageIcon userIcon;
	private Color purple = new Color(82, 12, 139);

	//DB
	private DB_reservation connect_reservation = new DB_reservation();
	private DB_MovieTimeTable connect_movieTimetable = new DB_MovieTimeTable();
	private DB_Theater coneect_theater = new DB_Theater();
	private DB_MovieInfo connect_movie  = new DB_MovieInfo();
	
	private Movie movie;
	private MovieTimtTable movieTimetable;
	private int MAX = 3;
	private Vector<Reservation> reseravations = new Vector<Reservation>();
	
	//text
	private String[] texts = {"������","��ǰ��","�̿��� / �ż�","���� ����"};
	public MyPage() {}
	public MyPage(User user) {
		
		super("���� ������");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);//âũ�� ����x
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);//���̾ƿ� null
		setVisible(true);
		
		
		
		reseravations = connect_reservation.getReservations(user.getUserID());
		this.user = user;
		
		//������ ����
		String str ="src/imges/"+user.getUserProfile()+".png";
		userIcon = new ImageIcon(str);
		profile_img.setBounds(POS_X_LEFT, PaddingTop, 400, 400);
		profile_img.setOpaque(false);
		profile_img.setBackground(Color.RED);
		profile_img.setIcon(userIcon);
		panel.add(profile_img);
		
		//����� �̸�
		name.setBounds(POS_X_LEFT + 100, PaddingTop + 420, 200, 50);
		name.setText("�̸�: "+user.getUserName());
		name.setFont(font1);
		name.setHorizontalAlignment(JLabel.CENTER);
		panel.add(name);
		
		//������ ����btn
		btn_modify_Profile.setBounds(POS_X_LEFT + 100,PaddingTop + 525,200,50);
		btn_modify_Profile.setIcon(imgReProfile);
		btn_modify_Profile.setBorderPainted(false);
		btn_modify_Profile.addActionListener(new BtnEvent());
		panel.add(btn_modify_Profile);
		
		//���� ����
		userInfo.setBounds(POS_X_CENTER - 50,PaddingTop,150,50);
		userInfo.setOpaque(false);
		userInfo.setIcon(imgInfo);
		panel.add(userInfo);
		
		//���̵�
		id.setBounds(POS_X_CENTER, PaddingTop+60, 300, 40);
		id.setOpaque(true);
		id.setBackground(Color.WHITE);
		id.setFont(font2);
		id.setText("���̵�: "+user.getUserID());
		panel.add(id);
		
		//������� : yyyy-mm-dd
		birthday.setBounds(POS_X_CENTER, PaddingTop + 120, 300, 40);
		birthday.setOpaque(true);
		birthday.setBackground(Color.WHITE);
		birthday.setFont(font2);
		birthday.setText("�������: "+user.getUseryymmdd());
		panel.add(birthday);
		
		//��ȭ��ȣ
		phone.setBounds(POS_X_CENTER, PaddingTop + 180, 300, 40);
		phone.setOpaque(true);
		phone.setBackground(Color.WHITE);
		phone.setFont(font2);
		phone.setText("��ȭ��ȣ: "+user.getUserPhone());
		panel.add(phone);
		
		//��ȭ ����
		taste.setBounds(POS_X_CENTER, PaddingTop + 240, 300, 40);
		taste.setOpaque(true);
		taste.setBackground(Color.WHITE);
		taste.setFont(font2);
		taste.setText("��ȭ����: "+user.getUserTaste1());
		panel.add(taste);
		
		//������������btn
		btn_modify_Info.setBounds(POS_X_CENTER + 400, PaddingTop, 200, 50);
		btn_modify_Info.setIcon(imgReInfo);
		btn_modify_Info.setBorderPainted(false);
		btn_modify_Info.addActionListener(new BtnEvent());
		panel.add(btn_modify_Info);
		
		//�ֱ� ������ ��ȭ label
		record_movie.setBounds(POS_X_CENTER - 50, PaddingTop + 300, 250, 50);
		record_movie.setOpaque(false);
		record_movie.setIcon(imgRecord);
		panel.add(record_movie);
		
		//�ֱ� ������ ��ȭ
		record_movieInfo.setHorizontalAlignment(JLabel.CENTER);
		record_movieInfo.setBounds(POS_X_CENTER - 50, PaddingTop + 365, 650, 250);
		record_movieInfo.setFont(font2);
		record_movieInfo.setBorder(new LineBorder(purple, 1));
		panel.add(record_movieInfo);
		
		
		//Panel
		panel.setBackground(Color.WHITE);
		panel.setBounds(0,(int) (Main.SCREEN_HEIGHT*0.25),Main.SCREEN_WIDTH,(int)(Main.SCREEN_HEIGHT*0.75));
		panel.setLayout(null);
		add(panel);
		
		
		/*
		 * for(int i =0; i<recent_movieInfo[0].length; i++) {
		 * 
		 * recent_movieInfo[0][i] = new JLabel(texts[i]);
		 * panel.add(recent_movieInfo[0][i]);
		 * recent_movieInfo[0][i].setBounds(POS_X_CENTER - 50+(i*160),
		 * PaddingTop+300+(250/4)*(1)+3, 150, 250/4);
		 * recent_movieInfo[0][i].setBorder(new LineBorder(purple, 1));
		 * recent_movieInfo[0][i].setBorder(new LineBorder(purple, 1));
		 * recent_movieInfo[0][i].setHorizontalAlignment(JLabel.CENTER);
		 * recent_movieInfo[0][i].setFont(font2);
		 * recent_movieInfo[0][i].setBackground(Color.red);
		 * 
		 * }
		 */
		
		if(reseravations.size()>0) {
			int num = reseravations.size();
			
			if(reseravations.size()>MAX) {
				num = 4;
			}else{
				num = reseravations.size()+1;
			}
			for(int y=0; y<3; y++) {
				System.out.println(num);
				for(int x=0; x<recent_movieInfo[y].length; x++){
					//�⺻ ����
					recent_movieInfo[y][x] = new JLabel("aa");
					recent_movieInfo[y][x].setBorder(new LineBorder(purple, 1));
					recent_movieInfo[y][x].setBorder(new LineBorder(purple, 1));
					recent_movieInfo[y][x].setFont(font2);
					recent_movieInfo[y][x].setBackground(Color.red);
					recent_movieInfo[y][x].setHorizontalAlignment(JLabel.CENTER);
					//recent_movieInfo[y][x].setBounds(POS_X_CENTER - 50+(x*160), PaddingTop+300+(250/4)*(y+1)+3, 150, 250/4);
				    panel.add(recent_movieInfo[y][x]);
					
				    //���
				    if(y == 0)
				    {
				    	recent_movieInfo[y][x].setText(texts[x]);
				    }
				    if(x == 0 )
				    {
				    	recent_movieInfo[y][x].setBounds(POS_X_CENTER - 50, PaddingTop+300+(250/4)*(y+1)+3, 130, 250/4);
				    }else if(x == 1)
				    {
				    	recent_movieInfo[y][x].setBounds(POS_X_CENTER - 50+130, PaddingTop+300+(250/4)*(y+1)+3, 250, 250/4);
				    }else if(x == 2)
				    {
				    	recent_movieInfo[y][x].setBounds(POS_X_CENTER - 50+130+250, PaddingTop+300+(250/4)*(y+1)+3, 140, 250/4);
				    }else if(x == 3) {
				    	recent_movieInfo[y][x].setBounds(POS_X_CENTER - 50+130+250+140, PaddingTop+300+(250/4)*(y+1)+3, 130, 250/4);
				    }
					
				    if(y!=0)
				    {
				    	int number = y-1;
				    	int movieTimetableKey = reseravations.get(number).getMovieTimetable();
					    movieTimetable = connect_movieTimetable.getMovieArea(movieTimetableKey);
					    int movieKey = movieTimetable.getMovieKey();
					    movie = connect_movie.getMovie(movieKey); String yymmdd, movieName,movieYYMMDD,seatCount;
					    //������	��ǰ��	�̿���/�ż�		�������  
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
				    
						
					
					/*
					 * recent_movieInfo[i].setText("<html>"
					 * +"  ������: "+yymmdd+"&nbsp;&nbsp;&nbsp;&nbsp;"+
					 * movieName+"&nbsp;&nbsp;&nbsp;&nbsp;" +seatCount+"</html>" );//+
					 * "&nbsp;&nbsp;" + country+"&nbsp;&nbsp;"+hall+"</html>");
					 */				
				    }
				
			}
		}else {
			record_movieInfo.setText("���� �ֱ� �����Ͻ� ������ �����ó׿� !");
		}
	}
	
	public class BtnEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btn_modify_Info) {
				String pw = JOptionPane.showInputDialog(null,"��й�ȣ�� �Է����ּ���");
				if(pw != null) {
					if(pw.equals(user.getUserPassword())) {
						new UserInformationPage(user);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					}
					
				}
				
			}
			
			if(e.getSource() == btn_modify_Profile) {
				new ChangeProfile(user);
				dispose();
			}
		}
		
	}
	
}