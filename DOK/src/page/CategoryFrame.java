package page;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import User.DB_userInfo;
import User.User;

public class CategoryFrame extends JFrame {

	// ��ġ
	final static int loginBtn_X = (int) (Main.SCREEN_WIDTH * 0.7);
	final static int SigUpBtn_X = loginBtn_X + 160;

	// component
	protected JPanel topPanel = new JPanel();
	private JLabel logo = new JLabel("DoK");
	private JButton[] category = new JButton[4];
	private String[] strCategory = { "HOME", "MOVIE", "RESERVATION", "MY PAGE" };
	protected JButton iconLogin = new JButton();
	protected JButton iconSignUp = new JButton();
	protected JButton iconLogout = new JButton();

	// �̹�����
	private ImageIcon imgLogo = new ImageIcon("src/imges/dok.png"); // �ΰ� �̹���
	protected ImageIcon imgLogin = new ImageIcon("src/img/login.png");
	protected ImageIcon imgSignUp = new ImageIcon("src/img/sign_up.png");
	protected ImageIcon imgLogout = new ImageIcon("src/img/logout.png");

	// font
	protected Font Logo = new Font("Franklin Gothic Heavy", Font.PLAIN, 120);
	protected Font font1 = new Font("�����ٸ����", Font.PLAIN, 20);
	protected Font font2 = new Font("�����ٸ����", Font.BOLD, 40);
	protected Font font3 = new Font("Franklin Gothic Heavy", Font.PLAIN, 30);

	//����
	public boolean startRunReservation_start = false;
	public boolean startRunMovieSit = false;
	
	//������
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("src/imges/p_octopus.png");
	
	// user����
	protected User user = new User();

	// DB
	private DB_userInfo connect_user = new DB_userInfo();

	public CategoryFrame() {

	}

	public CategoryFrame(String str) {
		super(str);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new windowAdapter());

		setIconImage(img);
		
		
		topPanel.setBackground(Color.WHITE);
		topPanel.setBounds(0, 0, Main.SCREEN_WIDTH, (int) (Main.SCREEN_HEIGHT * 0.25));
		topPanel.setLayout(null);
		add(topPanel);

		logo.setBounds(625, 10, 275, 150);
		logo.setFont(Logo);
		topPanel.add(logo);

		// ī�װ�
		for (int i = 0; i < 4; i++) {
			category[i] = new JButton();
			category[i].setText(strCategory[i]);
			category[i].setFont(font3);
			category[i].setBounds(200 + (i * 270), 180, 275, 50);
			category[i].setBackground(Color.WHITE);
			// setFocusPainted(false);
			// btn_category[i].setBorderPainted(false);
			topPanel.add(category[i]);
			category[i].addActionListener(new categoryEvent());

		}

	}

	class categoryEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			startRunReservation_start = false;
			startRunMovieSit = false;
			if (e.getSource() == category[0]) {// Ȩ
				new DOKPage(user);
				dispose();
			} else if (e.getSource() == category[1]) {// ��ȭ
				new ChartPage(user);
				dispose();
			} else if (e.getSource() == category[2]) {// ����
				Thread td = new Thread(new Reservation_start_page(user));
				td.start();
				dispose();
				// new MovieSitPage();
			} else if (e.getSource() == category[3]) {// ���� ������
				if (user.getUserIsLogin()) {
					new MyPage(user);
					dispose();
				} else {
					JOptionPane message = new JOptionPane();// �޽��� �ڽ� ��ü
					message.showMessageDialog(null, "�α��� �� �̿����ּ���");
					new DOKPage(user);
					dispose();
				}

			}

			if (e.getSource() == iconLogin) {
				new LoginPage();
				dispose();
			} else if (e.getSource() == iconSignUp) {
				new SignUpPage();
				dispose();
			} else if (e.getSource() == iconLogout) {
				connect_user.updateIsLogout(user.getUserID());
				user = new User();
				dispose();
				new DOKPage(user);

			}
		}
	}

	// ������ X��ư�� ��������
	public class windowAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "DOK�� ���� �Ͻðڽ��ϱ�?");
			if (result == JOptionPane.OK_OPTION) {
				if (!user.getUserIsLogin()) {
					// System.out.println("�α��� �ȵǾ��ֽ�");
					System.exit(0);
				} else {
					connect_user.updateIsLogout(user.getUserID());
					System.exit(0);
				}

			}
		}
	}
}
