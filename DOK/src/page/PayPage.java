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
	private JLabel howMuch = new JLabel("���������ݾ� :");
	private JLabel price = new JLabel();
	private JLabel choice = new JLabel("���� ����� �������ּ���.");
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton card = new JRadioButton("�ſ� ī��", true);
	private JRadioButton cash = new JRadioButton("������ �Ա�");

	// ī�� ����
	private JLabel cardCompany = new JLabel("ī��縦 �������ּ���");
	private JLabel depositBank = new JLabel("�Ա������� �������ּ���");
	private String[] company = { "���� ī��", "�츮 ī��", "�ϳ� ī��", "���� ī��", "�Ե� ī��", "�Ｚ ī��", "NH ī��", "BC ī��" };
	private String[] bank = { "��������", "�츮����", "��������", "KEB�ϳ�(����)����", "�������", "�뱸����" };
	private JLabel userName = new JLabel("������ ���� �Է��ϼ���");
	private JTextField inputName = new JTextField();
	private JComboBox checkCompany = new JComboBox(company);
	private JComboBox checkBank = new JComboBox(bank);
	private JLabel cardNumber = new JLabel("ī�� ��ȣ�� �Է��ϼ���");
	private JTextField inputCardNumber = new JTextField();
	private JLabel cardPassword = new JLabel("ī�� ��й�ȣ 4�ڸ� �Է����ּ���");
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
	
	// ��¥
	private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	// design
	private Font compo = new Font("�����ٸ����", Font.BOLD, 20);
	private Font input = new Font("�����ٸ����", Font.PLAIN, 15);
	
	//today
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",Locale.KOREA);
	private Calendar calendar = Calendar.getInstance();
	String today = sdf.format(calendar.getTime()).toString();
	
	public PayPage(User user, Reservation reservation, MovieTimtTable movieArea, int num_adult, int num_teen, int num_kids) {

		addWindowListener(new windowAdapter());
		setIconImage(img);
		// ��������
		howMuch.setBounds(PaddingLeft, PaddingTop - 115, 275, 50);
		howMuch.setFont(compo);
		panel.add(howMuch);

		moviePrice = num_adult * Movie.ADULT + num_teen * Movie.TEEN + num_kids * Movie.KIDS;
		price.setBounds(PaddingLeft + 150, PaddingTop - 115, 275, 50);
		price.setFont(new Font("�����ٸ����", Font.PLAIN, 20));
		price.setText(moviePrice + " ��");
		panel.add(price);

		startPayPage = true;

		// ���� ��� ���� ���̺�
		choice.setBounds(PaddingLeft, PaddingTop, 275, 50);
		choice.setFont(compo);
		panel.add(choice);

		// ����
		this.reservation = reservation;
		this.reservation.setYymmdd(today);
		this.user = user;
		this.movieArea = movieArea;
		this.num_adult = num_adult;
		this.num_teen = num_teen;
		this.num_kids = num_kids;
		
		// ���� �׷����� ��� A or B�� �����
		g.add(card);
		g.add(cash);

		// A : ī�� ����
		card.setBounds(PaddingLeft + 10, PaddingTop + 50, 100, 50);
		card.setFont(input);
		card.setBackground(Color.WHITE);
		card.addActionListener(new RadioBtnEvent());
		panel.add(card);

		// B : ���� ����
		cash.setBounds(PaddingLeft + 150, PaddingTop + 50, 100, 50);
		cash.setFont(input);
		cash.setBackground(Color.WHITE);
		cash.addActionListener(new RadioBtnEvent());
		panel.add(cash);

		// ī��� ����
		cardCompany.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		cardCompany.setFont(compo);
		panel.add(cardCompany);

		// ī��� ���� : JComboBox
		checkCompany.setBounds(PaddingLeft + 10, PaddingTop + 240, 300, 35);
		checkCompany.setFont(input);
		panel.add(checkCompany);

		// �Ա� ���� �����ϱ�
		depositBank.setVisible(false);
		depositBank.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		depositBank.setFont(compo);
		panel.add(depositBank);

		// ���� ����
		checkBank.setBounds(PaddingLeft + 10, PaddingTop + 240, 300, 35);
		checkBank.setFont(input);
		panel.add(checkBank);

		// ������ �� �Է�
		userName.setVisible(false);
		userName.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		userName.setFont(compo);
		panel.add(userName);

		// ������ �� �Է� : JTextField
		inputName.setVisible(false);
		inputName.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		inputName.setFont(input);
		panel.add(inputName);

		// ī�� ��ȣ �Է�
		cardNumber.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		cardNumber.setFont(compo);
		panel.add(cardNumber);

		// ī�� ��ȣ �Է� : JTextField
		inputCardNumber.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		inputCardNumber.setFont(input);
		panel.add(inputCardNumber);

		// ī�� ��й�ȣ �Է� 4��
		cardPassword.setBounds(PaddingLeft, PaddingTop + 465, 300, 50);
		cardPassword.setFont(compo);
		panel.add(cardPassword);

		// ī�� ��й�ȣ �Է� 4�� : JTextField
		inputCardPassword.setBounds(PaddingLeft + 10, PaddingTop + 540, 300, 35);
		inputCardPassword.setFont(input);
		panel.add(inputCardPassword);

		// ���� Ȯ��
		finish.setBounds(250 - 75, PaddingTop + 600, 150, 50);
		finish.setIcon(imgFinish);
		finish.addActionListener(this);
		finish.setBorderPainted(false);
		panel.add(finish);

		add(panel);

		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		setTitle("��������");
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
				// ī��
				howPay = card.getText();
				cardCompany.setVisible(true);
				checkCompany.setVisible(true);
				cardNumber.setVisible(true);
				inputCardNumber.setVisible(true);
				cardPassword.setVisible(true);
				inputCardPassword.setVisible(true);

				// ����
				depositBank.setVisible(false);
				checkBank.setVisible(false);
				userName.setVisible(false);
				inputName.setVisible(false);
			} else {
				// ����
				howPay = cash.getText();
				depositBank.setVisible(true);
				checkBank.setVisible(true);
				userName.setVisible(true);
				inputName.setVisible(true);

				// ī��
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
				new PayTimer(user, movieArea, reservation, num_adult, num_teen, num_kids);// payŸ�̸�
				dispose();
			} else {
				JOptionPane message = new JOptionPane();
				message.showMessageDialog(null, "�Էµ��� ���� ������ �ֽ��ϴ�.");

			}
		}
	}

	class windowAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "���Ÿ� ����Ͻðڽ��ϱ�?");
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
					JOptionPane message = new JOptionPane();// �޽��� �ڽ� ��ü
					message.showMessageDialog(null, "���� �ð��� �ʰ� �ῴ���ϴ�. �ٽ� ���� �������� ���ư��ϴ�.");
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