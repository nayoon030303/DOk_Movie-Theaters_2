package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import User.DB_userInfo;
import User.User;

public class LoginPage extends JFrame{
	
	//��ġ 
	private final static int C_X = 150;
	private final static int IDLable_Y = 200;
	private final static int IDTextF_Y = 290;
	private final static int PWLable_Y = 400;
	private final static int PWTextF_Y = 490;
	private final static int LoginBtn_Y = 700;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("src/imges/p_octopus.png");
	//user
	private  User user = new User();
	
	//component
	private JPanel panel = new JPanel();
	private JButton iconBack = new JButton();
	private JLabel id = new JLabel("ID");
	private JTextField inputId = new JTextField();
	private JLabel password = new JLabel("PassWord");
	private JPasswordField inputPassword = new JPasswordField();
	private JButton iconLogin = new JButton();
	private JButton iconSignUp = new JButton();
	private JLabel logo = new JLabel();

	//�̹���
	private ImageIcon imgLogo = new ImageIcon("src/imges/dok.png");
	
	//DB���� Ŭ����
	private DB_userInfo connection = new DB_userInfo();	
	
	private String userID,userPassword;
	
	//�̹���
	private ImageIcon imgLogin = new ImageIcon("src/img/btnLogin.png");
	private ImageIcon imgSignup = new ImageIcon("src/img/btnSignup.png");
	private ImageIcon imgBack = new ImageIcon("src/img/back.png");
	
	//font
	private Font Logo = new Font("Franklin Gothic Heavy", Font.PLAIN, 120);
	private Font font1 = new Font("�����ٸ����", Font.BOLD, 30);
	private Font font2 = new Font("�����ٸ����", Font.PLAIN, 20);
	
	public LoginPage() {
		super("Login");
		setSize(600, Main.SCREEN_HEIGHT);
		setResizable(false);
		setIconImage(img);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);	//���̾ƿ� null
		setVisible(true);	
		
		//�г�
		panel.setBounds(0,0,600,Main.SCREEN_HEIGHT);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		add(panel);
		
		// �̹��� label
		//jlLogo.setIcon(imgLogo);
		logo.setText("DoK");
		logo.setFont(Logo);
		logo.setBounds(175, 50,275,100);
		panel.add(logo);
		
		iconBack.setIcon(imgBack);
		iconBack.setBounds(5, 15, 100, 40);
		iconBack.setOpaque(true);
		iconBack.setBackground(Color.WHITE);
		iconBack.setBorderPainted(false);
		panel.add(iconBack);
		
		
		//���̵� label
		id.setBounds(C_X,IDLable_Y,200,100);
		id.setFont(font1);
		panel.add(id);
		
		//���̵� textField
		inputId.setBounds(C_X, IDTextF_Y, 250, 50);
		inputId.setFont(font2);
		panel.add(inputId);
		
		//�н����� label
		password.setBounds(C_X,PWLable_Y,200,100);
		password.setFont(font1);
		panel.add(password);
		
		//�н����� textField
		inputPassword.setBounds(C_X,PWTextF_Y,250,50);
		inputPassword.setFont(font2);
		panel.add(inputPassword);
		
		//�α��� ��ư
		iconLogin.setIcon(imgLogin);
		iconLogin.setBounds(C_X,LoginBtn_Y,300,80);
		iconLogin.setBorderPainted(false);
		panel.add(iconLogin);
		
		//ȸ������ ��ư
		iconSignUp.setIcon(imgSignup);
		iconSignUp.setBounds(C_X,LoginBtn_Y+100,300,80);
		iconSignUp.setBorderPainted(false);
		panel.add(iconSignUp);
		
		iconBack.addActionListener(new EventHandler());
		iconLogin.addActionListener(new EventHandler());
		iconSignUp.addActionListener(new EventHandler());
			
	}
	class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==iconLogin) {//�α��� ��ư
				
				userID = inputId.getText();
				userPassword = inputPassword.getText();
				//System.out.println(connection.getUserInfo(userID).getUserID());
				boolean success = connection.isUser(userID, userPassword);
				if(success) {//����
					success = connection.isLogin(userID);
					if(success) {
						user = connection.getUserInfo(userID);
						connection.updateIsLogin(userID);
						System.out.println(user.getUserIsLogin());
						new DOKPage(user);
						setVisible(false);
					}else {
						JOptionPane message =new JOptionPane();//�޽��� �ڽ� ��ü
						message.showMessageDialog(null,"�̹� �α���ó���� �Ǿ��ֽ��ϴ�." );
					}
					
				}else {
					JOptionPane message =new JOptionPane();//�޽��� �ڽ� ��ü
					message.showMessageDialog(null,"���̵� �Ǵ� �н����尡 ���� �ʽ��ϴ�. Ȯ�� ��  �Է����ּ���" );
				}
				
				
			}else if(e.getSource() == iconSignUp) {//ȸ������ ��ư
				new SignUpPage();
				dispose();
			}else if(e.getSource() == iconBack) {
				new DOKPage(user);
				dispose();
			}
			
		}
	}
}


