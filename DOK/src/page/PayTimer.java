package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Movie.DB_MovieArea;
import Movie.MovieArea;
import User.User;
import reservation.DB_ticket;
import reservation.Ticket;

public class PayTimer extends JFrame {
	private JPanel panel = new JPanel();
	private JLabel label1 = new JLabel("결제가 ");
	private JLabel label2 = new JLabel("진행 중입니다.");
	private JLabel label3 = new JLabel("잠시만 기다려주세요.");

	private ImageIcon imgLoading = new ImageIcon("src/img/loading250.gif");
	private JLabel iconLoading = new JLabel(imgLoading);
	// private ImageIcon imgLoadingimg;
	private MovieArea movieArea;
	private Ticket ticket;
	private User user;
	private int num_adult, num_teen, num_kids;
	private int WIDTH = 500;
	private int HEIGHT = 400;

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("src/imges/p_octopus.png");
	
	// DB
	private DB_ticket connect_ticket = new DB_ticket();
	private DB_MovieArea connect_movieArea = new DB_MovieArea();

	// Design
	private Font font1 = new Font("나눔바른고딕", Font.BOLD, 35);
	private Font font2 = new Font("나눔바른고딕", Font.PLAIN, 20);
	private Font font3 = new Font("휴먼둥근헤드라인", Font.PLAIN, 35);

	public PayTimer(User user, MovieArea movieArea, Ticket ticket, int num_adult, int num_teen, int num_kids) {

		setIconImage(img);
		setTitle("결제중입니다");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
		Container c = getContentPane(); // 패널 생성
		// c.setLayout(null); //플로우 레이아웃

		this.movieArea = movieArea;
		this.ticket = ticket;
		this.user = user;
		this.num_adult = num_adult;
		this.num_teen = num_teen;
		this.num_kids = num_kids;
		panel.setBackground(Color.WHITE);

		iconLoading.setOpaque(true);
		iconLoading.setBackground(Color.WHITE);
		iconLoading.setBounds(0, 20, WIDTH, 150);
		iconLoading.setHorizontalAlignment(JLabel.CENTER);
		panel.add(iconLoading);

		label1.setFont(font1);
		label1.setBounds(0, 170, WIDTH, 40);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setOpaque(true);
		label1.setBackground(Color.WHITE);
		panel.add(label1);

		label2.setFont(font1);
		label2.setBounds(0, 210, WIDTH, 40);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setOpaque(true);
		label2.setBackground(Color.WHITE);
		panel.add(label2);

		label3.setFont(font2);
		label3.setBounds(0, 250, WIDTH, 50);
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setOpaque(true);
		label3.setBackground(Color.WHITE);
		panel.add(label3);

		add(panel);
		panel.setLayout(null); // 플로우 레이아웃
		panel.setBackground(Color.WHITE);

		/*
		 * System.out.println(ticket.getUserID());
		 * System.out.println(ticket.getMovieareaKey());
		 * System.out.println(ticket.getPrice());
		 * System.out.println(ticket.getSeatCount());
		 * System.out.println(ticket.getSeatWhere());
		 * System.out.println(ticket.getYymmdd());
		 * System.out.println(ticket.getPayHow());
		 */
		conToDBMovieAreaInfo();

	}

	public synchronized void conToDBMovieAreaInfo() {
		int[][] now_seat = new int[9][24];
		int[][] db_seat = new int[9][24];

		db_seat = setReservationSeat(connect_movieArea.getMovieArea(movieArea.get_key()).getSeatState(), db_seat);
		now_seat = setReservationSeat(movieArea.getSeatState(), now_seat);
		int c = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 24; j++) {
				if (db_seat[i][j] == MovieSitPage.NO_SELECT && now_seat[i][j] == MovieSitPage.NOW_SELECT) {
					c++;
				}
			}
		}
		
		// 예매 가능한
		if (c == ticket.getSeatCount()) {
			movieArea.setSeatState(movieArea.getSeatState().replace("2", "1"));// 2를 1로 치환
			connect_ticket.addTicket(ticket.getUserID(), ticket.getMovieareaKey(), ticket.getPrice(), ticket.getSeatCount(),
					ticket.getSeatWhere(), ticket.getYymmdd(), ticket.getPayHow());
			connect_movieArea.updateMovieArea(movieArea.get_key(), movieArea.getVacantSeat() - ticket.getSeatCount(),
					movieArea.getSeatState());
			System.out.println("시작");
			Timer2 t2 = new Timer2(this, iconLoading, user, movieArea, ticket, num_adult,
			num_teen, num_kids); t2.start();
			 
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "이미 선택된 좌석입니다.");
			Reservation_start_page r1 = new Reservation_start_page(user);
			Thread th = new Thread(r1);
			th.start();
			dispose();
		}
		System.out.println("끝");
	}

	int[][] setReservationSeat(String cutStr, int[][] seeatSit) {
		StringTokenizer str = new StringTokenizer(cutStr, "/");
		String[] strI = new String[str.countTokens()];
		int n = 0;
		while (str.hasMoreElements()) {
			strI[n] = str.nextToken();
			n++;
		}
		// 예약된 좌석들 표시
		for (int i = 0; i < 9; i++) {// 9
			String[] strArray = strI[i].split("");
			int c = 0;
			for (String s : strArray) {// 24
				if (s.equals("1")) {
					seeatSit[i][c] = MovieSitPage.PRE_SELECT;
				} else if (s.equals("0")) {
					seeatSit[i][c] = MovieSitPage.NO_SELECT;
				}else if(s.equals("2")) {
					seeatSit[i][c] = MovieSitPage.NOW_SELECT;
				}
				c += 1;
			}
		}
		return seeatSit;
	}
}