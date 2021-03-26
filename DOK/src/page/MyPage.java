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

import Area.DB_Area;
import Movie.DB_MovieArea;
import Movie.DB_MovieInfo;
import Movie.Movie;
import Movie.MovieArea;
import User.User;
import reservation.DB_ticket;
import reservation.Ticket;
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
	private JLabel[] recent_movieInfo = new JLabel[4];
	private Vector<Ticket> tickets = new Vector<Ticket>();
	
	
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
	private DB_ticket connect_ticket = new DB_ticket();
	private DB_MovieArea connect_movieArea = new DB_MovieArea();
	private DB_Theater coneect_theater = new DB_Theater();
	private DB_MovieInfo connect_movie  = new DB_MovieInfo();
	
	public MyPage() {}
	public MyPage(User user) {
		
		super("���� ������");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);//âũ�� ����x
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);//���̾ƿ� null
		setVisible(true);
		
		
		
		tickets = connect_ticket.getTicket(user.getUserID());
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
		
		if(tickets.size()>0) {
			int num =0;
			if(tickets.size()>4) {
				num = 4;
			}else{
				num = tickets.size();
			}
			for(int i=0; i<num; i++) {
				recent_movieInfo[i] = new JLabel();
				recent_movieInfo[i].setBorder(new LineBorder(purple, 1));
				recent_movieInfo[i].setBackground(Color.red);
				recent_movieInfo[i].setBounds(POS_X_CENTER - 50, PaddingTop+300+(250/4)*(i+1)+3, 650, 250/4);
				String yymmdd,movieName,area,country,startTime,hall;
				int a = tickets.get(i).getMovieareaKey();
				MovieArea movieArea = connect_movieArea.getMovieArea(a);
				int areaKey = movieArea.getArea_key();
				Theater theater = coneect_theater.getTheater(areaKey);
				Movie movie = connect_movie.getMovie(movieArea.getMovieKey()); 
				yymmdd = tickets.get(i).getYymmdd();
				movieName = movie.getM_name();
				area = theater.getArea();
				country = theater.getCountry();
				startTime = movieArea.getStartTime();
				hall = movieArea.getHall();
				recent_movieInfo[i].setText("<html>" +yymmdd+"&nbsp;&nbsp;&nbsp;&nbsp;"+ movieName+"&nbsp;&nbsp;&nbsp;&nbsp;"+area + "&nbsp;&nbsp;" + country+"&nbsp;&nbsp;"+hall+"</html>");
				panel.add(recent_movieInfo[i]);
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