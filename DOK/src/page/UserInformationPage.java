package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Movie.DB_MovieInfo;
import User.DB_userInfo;
import User.User;

public class UserInformationPage extends JFrame {

	// pos
	private final static int PaddingLeft = 30;
	private final static int PaddingTop = 150;
	private final static int USER_WIDTH = 250;
	private final static int USER_Padding = 140;

	// user
	private User user;

	// component
	private JPanel panel = new JPanel();

	private JLabel logo = new JLabel();
	private JLabel id = new JLabel("아이디");
	private JLabel currentPassword = new JLabel("기존 비밀번호");
	private JLabel newPassword = new JLabel("신규 비밀번호");
	private JLabel birthday = new JLabel("생년월일");
	private JLabel phone = new JLabel("전화번호");
	private JLabel taste = new JLabel("영화 취향");
	private JLabel userID = new JLabel();
	private JPasswordField inputCurrentPassword = new JPasswordField();
	private JPasswordField inputNewPassword = new JPasswordField();
	private JLabel userBirthday = new JLabel();
	private JTextField inputPhone = new JTextField();
	private String[] strTaste = { "공포", "드라마", "로맨스", "스릴러", "애니메이션", "액션", "SF" };
	private ButtonGroup groupTaste = new ButtonGroup();
	private JRadioButton[] radioTaste = new JRadioButton[7];
	private JButton iconFinish = new JButton();
	
	//이미지
	private ImageIcon imgFinish = new ImageIcon("src/imges/RE.png");

	// design
	private Font Logo = new Font("Franklin Gothic Heavy", Font.PLAIN, 85);
	private Font lfont = new Font("나눔바른고딕", Font.BOLD, 20);
	private Font rfont = new Font("나눔바른고딕", Font.PLAIN, 17);
	private Color purple = new Color(82, 12, 139);

	public UserInformationPage(User user) {
		this.user = user;

		// 로고
		logo.setText("DoK");
		logo.setBounds(150, 25, 200, 100);
		logo.setFont(Logo);
		panel.add(logo);

		// 아이디
		id.setBounds(PaddingLeft, PaddingTop, 130, 50);
		id.setFont(lfont);
		id.setForeground(purple);
		id.setHorizontalAlignment(JLabel.CENTER);
		panel.add(id);

		userID.setBounds(PaddingLeft + USER_Padding, PaddingTop, USER_WIDTH, 50);
		userID.setOpaque(true);
		userID.setBackground(Color.WHITE);
		userID.setText(user.getUserID());
		userID.setFont(rfont);
		userID.setHorizontalAlignment(JLabel.CENTER);
		panel.add(userID);

		// current 비밀번호
		currentPassword.setBounds(PaddingLeft, PaddingTop + 60, 130, 50);
		currentPassword.setFont(lfont);
		currentPassword.setForeground(purple);
		currentPassword.setHorizontalAlignment(JLabel.CENTER);
		panel.add(currentPassword);

		inputCurrentPassword.setBounds(PaddingLeft + USER_Padding, PaddingTop + 60, USER_WIDTH, 50);
		inputCurrentPassword.setOpaque(true);
		inputCurrentPassword.setBackground(Color.WHITE);
		inputCurrentPassword.setFont(rfont);
		inputCurrentPassword.setHorizontalAlignment(JLabel.CENTER);
		panel.add(inputCurrentPassword);

		// new 비밀번호
		newPassword.setBounds(PaddingLeft, PaddingTop + 120, 130, 50);
		newPassword.setFont(lfont);
		newPassword.setForeground(purple);
		newPassword.setHorizontalAlignment(JLabel.CENTER);
		panel.add(newPassword);

		inputNewPassword.setBounds(PaddingLeft + USER_Padding, PaddingTop + 120, USER_WIDTH, 50);
		inputNewPassword.setOpaque(true);
		inputNewPassword.setBackground(Color.WHITE);
		inputNewPassword.setFont(rfont);
		inputNewPassword.setHorizontalAlignment(JLabel.CENTER);
		panel.add(inputNewPassword);

		// 생년월일 : yyyy-mm-dd
		birthday.setBounds(PaddingLeft, PaddingTop + 180, 130, 50);
		birthday.setFont(lfont);
		birthday.setForeground(purple);
		birthday.setHorizontalAlignment(JLabel.CENTER);
		panel.add(birthday);

		userBirthday.setBounds(PaddingLeft + USER_Padding, PaddingTop + 180, USER_WIDTH, 50);
		userBirthday.setFont(rfont);
		userBirthday.setHorizontalAlignment(JLabel.CENTER);
		userBirthday.setOpaque(true);
		userBirthday.setBackground(Color.WHITE);
		userBirthday.setText(user.getUseryymmdd());
		panel.add(userBirthday);

		// 전화번호
		phone.setBounds(PaddingLeft, PaddingTop + 240, 130, 50);
		phone.setFont(lfont);
		phone.setForeground(purple);
		phone.setHorizontalAlignment(JLabel.CENTER);
		panel.add(phone);

		inputPhone.setBounds(PaddingLeft + USER_Padding, PaddingTop + 240, USER_WIDTH, 50);
		inputPhone.setOpaque(true);
		inputPhone.setBackground(Color.WHITE);
		inputPhone.setText(user.getUserPhone());
		inputPhone.setFont(rfont);
		inputPhone.setHorizontalAlignment(JLabel.CENTER);
		panel.add(inputPhone);

		// 영화 취향
		taste.setBounds(PaddingLeft, PaddingTop + 300, 130, 50);
		taste.setFont(lfont);
		taste.setForeground(purple);
		taste.setHorizontalAlignment(JLabel.CENTER);
		panel.add(taste);

		int x = 0;
		int y = PaddingTop + 240;
		for (int i = 0; i < radioTaste.length; i++) {
			radioTaste[i] = new JRadioButton(strTaste[i]);
			if (i % 2 == 0) {
				x = PaddingLeft + USER_Padding;
				y += 60;
			} else {
				x = PaddingLeft + USER_Padding + 150;
			}
			radioTaste[i].setBounds(x, y, 130, 50);
			radioTaste[i].setOpaque(true);
			radioTaste[i].setBackground(Color.WHITE);
			radioTaste[i].setFont(rfont);
			groupTaste.add(radioTaste[i]);
			panel.add(radioTaste[i]);
		}

		// 수정
		iconFinish.setBounds(175, PaddingTop + 600, 150, 50);
		iconFinish.setOpaque(false);
		iconFinish.setIcon(imgFinish);
		iconFinish.addActionListener(new BtnEvent());
		panel.add(iconFinish);

		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		setSize(500, 900);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setResizable(false);

		WindowListener listener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(null, "수정페이지를 종료 하시겠습니까?");
				if (result == JOptionPane.OK_OPTION) {
					new MyPage(user);
					// connect_user.updateIsLogout(user.getUserID());
					setVisible(false);
					dispose();
				}
			}
		};
		addWindowListener(listener);

	}

	class BtnEvent implements ActionListener {
		private String currentPassword = inputCurrentPassword.getText();
		private String newPassword = inputNewPassword.getText();

		// private User user = new User();

		DB_userInfo connection = new DB_userInfo();

		@Override
		public void actionPerformed(ActionEvent e) {
			String id = user.getUserID();
			String current_pw = inputCurrentPassword.getText();
			String new_pw = inputNewPassword.getText();
			String phone = inputPhone.getText();
			String taste = null;
			for (int i = 0; i < strTaste.length; i++) {
				if (radioTaste[i].isSelected()) {
					taste = strTaste[i];
				}
			}

			// TODO Auto-generated method stub
			if (e.getSource() == iconFinish) {
				if (id.isEmpty() && new_pw.isEmpty() && phone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "정보를 입력해주세요");
				} else if (!current_pw.equals(user.getUserPassword())) {
					JOptionPane.showMessageDialog(null, "기존 비밀번호가 일치 하지 않습니다.");
				} else {
					connection.updateUserInfo(id, new_pw, phone, taste);
					user = connection.getUserInfo(id);
					dispose();
					new MyPage(user);
				}
			}
		}
	}
}
