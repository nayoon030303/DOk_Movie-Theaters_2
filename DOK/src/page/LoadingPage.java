package page;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import User.User;

class Wait extends Thread {
	private JFrame frame;
	private JLabel timerLabel;
	private int n;
	private User user = new User();
	private JFrame f;
	
	
	public Wait(JFrame frame, JLabel timerLabel, User user) {
		this.frame = frame;
		this.timerLabel = timerLabel;
		this.user = user;
		//9f.setVisible(false);
	}

	@Override
	public void run() {
		n = 5;

		while (true) {
			n--;
			try {
				if (n < 0) {
					f = new DOKPage(user);
					f.setVisible(true);
					frame.dispose();
					f.setVisible(true);
					stop();

				}
				sleep(1000); // 1/1000초 단위
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}

public class LoadingPage extends JFrame {
	// component
	private JPanel panel = new JPanel();
	private JLabel iconWelcome = new JLabel();
	private JLabel iconLoading = new JLabel();
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("src/imges/p_octopus.png");
	
	// 이미지
	private ImageIcon imgWelcome = new ImageIcon("src/img/welcome.png");
	private ImageIcon imgLoading = new ImageIcon("src/img/loading.gif");

	public LoadingPage(User user) {
		super("DOK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(img);
		iconWelcome.setIcon(imgWelcome);
		iconWelcome.setBounds(0, 0, 1500, 600);
		panel.add(iconWelcome);

		iconLoading.setIcon(imgLoading);
		iconLoading.setBounds(350, 590, 800, 290);
		panel.add(iconLoading);

		add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		setSize(1500, 1000);
		setResizable(false);
		setVisible(true);

		Wait wait = new Wait(this, iconWelcome, user);

		wait.start();
	}
}