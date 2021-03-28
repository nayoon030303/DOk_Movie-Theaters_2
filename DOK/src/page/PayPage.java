package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Movie.Movie;
import Movie.MovieTimtTable;
import User.User;
import page.CategoryFrame.windowAdapter;
import page.MyPage.BtnEvent;
import reservation.Reservation;

public class PayPage extends JFrame implements ActionListener, Runnable {
	private final static int PaddingLeft = 50;
	private final static int PaddingTop = 175;
	private final static int PRICE_Y = 50;

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("src/imges/p_octopus.png");
	
	
	// component
	private JPanel panel = new JPanel();
	private JLabel howMuch = new JLabel("최종결제금액 :");
	private JLabel price = new JLabel();
	private JLabel choice = new JLabel("결제 방법을 선택해주세요.");
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton card = new JRadioButton("신용 카드", true);
	private JRadioButton cash = new JRadioButton("무통장 입금");

	// 카드 결제
	private JLabel cardCompany = new JLabel("카드사를 선택해주세요");
	private JLabel depositBank = new JLabel("입금은행을 선택해주세요");
	private String[] company = { "국민 카드", "우리 카드", "하나 카드", "신한 카드", "롯데 카드", "삼성 카드", "NH 카드", "BC 카드" };
	private String[] bank = { "농협은행", "우리은행", "신한은행", "KEB하나(외한)은행", "기업은행", "대구은행" };
	private JLabel userName = new JLabel("예금주 명을 입력하세요");
	private JTextField inputName = new JTextField();
	private JComboBox checkCompany = new JComboBox(company);
	private JComboBox checkBank = new JComboBox(bank);
	private JLabel cardNumber = new JLabel("카드 번호를 입력하세요");
	private JTextField inputCardNumber = new JTextField();
	private JLabel cardPassword = new JLabel("카드 비밀번호 4자를 입력해주세요");
	private JTextField inputCardPassword = new JTextField();
	private JButton finish = new JButton();
	private ImageIcon imgFinish = new ImageIcon("src/imges/finish.png");

	private String howPay = card.getText();

	private Reservation reservation;
	private User user;
	private MovieTimtTable movieArea;
	private int num_adult, num_teen, num_kids;
	private boolean startPayPage = false;
	private int moviePrice;
	
	// 날짜
	private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	// design
	private Font compo = new Font("나눔바른고딕", Font.BOLD, 20);
	private Font input = new Font("나눔바른고딕", Font.PLAIN, 15);
	
	//today
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",Locale.KOREA);
	private Calendar calendar = Calendar.getInstance();
	String today = sdf.format(calendar.getTime()).toString();
	
	public PayPage(User user, Reservation reservation, MovieTimtTable movieArea, int num_adult, int num_teen, int num_kids) {

		addWindowListener(new windowAdapter());
		setIconImage(img);
		// 최종결제
		howMuch.setBounds(PaddingLeft, PaddingTop - 115, 275, 50);
		howMuch.setFont(compo);
		panel.add(howMuch);

		moviePrice = num_adult * Movie.ADULT + num_teen * Movie.TEEN + num_kids * Movie.KIDS;
		price.setBounds(PaddingLeft + 150, PaddingTop - 115, 275, 50);
		price.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		price.setText(moviePrice + " 원");
		panel.add(price);

		startPayPage = true;

		// 결제 방법 선택 레이블
		choice.setBounds(PaddingLeft, PaddingTop, 275, 50);
		choice.setFont(compo);
		panel.add(choice);

		// 정보
		this.reservation = reservation;
		this.reservation.setYymmdd(today);
		this.user = user;
		this.movieArea = movieArea;
		this.num_adult = num_adult;
		this.num_teen = num_teen;
		this.num_kids = num_kids;
		
		// 라디오 그룹으로 묶어서 A or B로 만들기
		g.add(card);
		g.add(cash);

		// A : 카드 결제
		card.setBounds(PaddingLeft + 10, PaddingTop + 50, 100, 50);
		card.setFont(input);
		card.setBackground(Color.WHITE);
		card.addActionListener(new RadioBtnEvent());
		panel.add(card);

		// B : 현금 결제
		cash.setBounds(PaddingLeft + 150, PaddingTop + 50, 100, 50);
		cash.setFont(input);
		cash.setBackground(Color.WHITE);
		cash.addActionListener(new RadioBtnEvent());
		panel.add(cash);

		// 카드사 선택
		cardCompany.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		cardCompany.setFont(compo);
		panel.add(cardCompany);

		// 카드사 선택 : JComboBox
		checkCompany.setBounds(PaddingLeft + 10, PaddingTop + 240, 300, 35);
		checkCompany.setFont(input);
		panel.add(checkCompany);

		// 입금 은행 선택하기
		depositBank.setVisible(false);
		depositBank.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		depositBank.setFont(compo);
		panel.add(depositBank);

		// 은행 선택
		checkBank.setBounds(PaddingLeft + 10, PaddingTop + 240, 300, 35);
		checkBank.setFont(input);
		panel.add(checkBank);

		// 예금주 명 입력
		userName.setVisible(false);
		userName.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		userName.setFont(compo);
		panel.add(userName);

		// 예금주 명 입력 : JTextField
		inputName.setVisible(false);
		inputName.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		inputName.setFont(input);
		panel.add(inputName);

		// 카드 번호 입력
		cardNumber.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		cardNumber.setFont(compo);
		panel.add(cardNumber);

		// 카드 번호 입력 : JTextField
		inputCardNumber.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		inputCardNumber.setFont(input);
		panel.add(inputCardNumber);

		// 카드 비밀번호 입력 4자
		cardPassword.setBounds(PaddingLeft, PaddingTop + 465, 300, 50);
		cardPassword.setFont(compo);
		panel.add(cardPassword);

		// 카드 비밀번호 입력 4자 : JTextField
		inputCardPassword.setBounds(PaddingLeft + 10, PaddingTop + 540, 300, 35);
		inputCardPassword.setFont(input);
		panel.add(inputCardPassword);

		// 결제 확인
		finish.setBounds(250 - 75, PaddingTop + 600, 150, 50);
		finish.setIcon(imgFinish);
		finish.addActionListener(this);
		finish.setBorderPainted(false);
		panel.add(finish);

		add(panel);

		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		setTitle("최종결제");
		setSize(500, 900);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	class RadioBtnEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == card) {
				// 카드
				howPay = card.getText();
				cardCompany.setVisible(true);
				checkCompany.setVisible(true);
				cardNumber.setVisible(true);
				inputCardNumber.setVisible(true);
				cardPassword.setVisible(true);
				inputCardPassword.setVisible(true);

				// 현금
				depositBank.setVisible(false);
				checkBank.setVisible(false);
				userName.setVisible(false);
				inputName.setVisible(false);
			} else {
				// 현금
				howPay = cash.getText();
				depositBank.setVisible(true);
				checkBank.setVisible(true);
				userName.setVisible(true);
				inputName.setVisible(true);

				// 카드
				cardCompany.setVisible(false);
				checkCompany.setVisible(false);
				cardNumber.setVisible(false);
				inputCardNumber.setVisible(false);
				cardPassword.setVisible(false);
				inputCardPassword.setVisible(false);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == finish) {
			reservation.setPayHow(howPay);

			if ((card.isSelected()
					&& (inputCardNumber.getText().length() > 1 && inputCardPassword.getText().length() > 1))
					|| (cash.isSelected() && inputName.getText().length() > 1)) {
				reservation.setPrice(moviePrice);
				startPayPage = false;
				new PayTimer(user, movieArea, reservation, num_adult, num_teen, num_kids);// pay타이머
				dispose();
			} else {
				JOptionPane message = new JOptionPane();
				message.showMessageDialog(null, "입력되지 않은 정보가 있습니다.");

			}
		}
	}

	class windowAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "예매를 취소하시겠습니까?");
			if (result == JOptionPane.OK_OPTION) {
				startPayPage = false;
				dispose();
				new DOKPage(user);
			}
		}
	}

	@Override
	public void run() {
		int second = 0;
		while (startPayPage) {
			second += 1;
			//System.out.println(second);
			try {
				if (second > 30) {
					JOptionPane message = new JOptionPane();// 메시지 박스 객체
					message.showMessageDialog(null, "결제 시간을 초과 햐였습니다. 다시 메인 페이지로 돌아갑니다.");
					new DOKPage(user);
					dispose();
					startPayPage = false;

				}

				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}