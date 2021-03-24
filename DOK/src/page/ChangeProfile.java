package page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import User.DB_userInfo;
import User.User;
import page.PayPage.windowAdapter;

public class ChangeProfile extends JFrame {
   //user
   private User user;
   
   //component
   private JPanel panel = new JPanel();
   
   private JLabel iconOctopus = new JLabel();
   private JLabel iconBee = new JLabel();
   private JLabel iconFrog = new JLabel();
  
   private ButtonGroup group = new ButtonGroup();
   private JRadioButton[] radioButton = new JRadioButton[3];
   private String strImg[] = {"P_octopus", "P_bee", "P_frog"};
   
   private JButton btn_ok = new JButton();
  
   private ImageIcon imgOctopus = new ImageIcon("src/img/250_octopus.png");
   private ImageIcon imgBee = new ImageIcon("src/img/250_bee.png");
   private ImageIcon imgFrog = new ImageIcon("src/img/250_frog.png");
   private ImageIcon imgOk = new ImageIcon("src/imges/okBtn.png");
   private String profile = "";
   //DB
   private DB_userInfo connection = new DB_userInfo();
  
   private String result = "";
   
   public ChangeProfile(User user) {
      super("프로필 이미지 변경");
      
      this.user = user;
      
      iconOctopus.setIcon(imgOctopus);
      iconOctopus.setBounds(80, 75, 250, 250); //+ 285
      iconOctopus.setOpaque(true);
      iconOctopus.setBackground(Color.WHITE);
      panel.add(iconOctopus);
      
      iconBee.setIcon(imgBee);
      iconBee.setBounds(80 + 285, 75, 250, 250); //+ 285
      iconBee.setOpaque(true);
      iconBee.setBackground(Color.WHITE);
      panel.add(iconBee);
      
      iconFrog.setIcon(imgFrog);
      iconFrog.setBounds(80 + (2 * 285), 75, 250, 250); //+ 285
      iconFrog.setOpaque(true);
      iconFrog.setBackground(Color.WHITE);
      panel.add(iconFrog);
      
      for(int i = 0; i < radioButton.length; i++) {
         radioButton[i] = new JRadioButton();
         radioButton[i].setBounds(200+(i * 285), 350, 25, 25);
         radioButton[i].setOpaque(true);
         radioButton[i].setBackground(Color.WHITE);
         group.add(radioButton[i]);
         panel.add(radioButton[i]);
         
         radioButton[i].addActionListener(new ChangeEvent());
      }
      
      btn_ok.setBounds(425, 400, 150, 50);
      btn_ok.setIcon(imgOk);
      btn_ok.setOpaque(true);
      btn_ok.setBackground(Color.WHITE);
      btn_ok.setBorderPainted(false);
      btn_ok.addActionListener(new ChangeEvent());
      panel.add(btn_ok);
      
      
      panel.setBackground(Color.WHITE);
      panel.setLayout(null);
      add(panel);
      addWindowListener(new windowAdapter());
      setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
      setSize(1000, 500);
      setResizable(false);
      setVisible(true);
   }
   
   class ChangeEvent implements ActionListener {
      
      public void actionPerformed(ActionEvent e) {
         for(int i = 0; i < radioButton.length; i++) {
            if(e.getSource() == radioButton[i]) {
               profile = strImg[i];
               //System.out.println(profile);
               break;
            }
         }
         

         if(e.getSource() == btn_ok) {
        	if(profile=="") {
        		
        	}else {
        		 connection.updateUserProfile(user.getUserID(),profile);
                 user = connection.getUserInfo(user.getUserID());
                 dispose();
                 new MyPage(user);
        	}
           
         }
      }
      
   }
   class windowAdapter extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
	        int result = JOptionPane.showConfirmDialog(null,"프로필 변경을 취소하시겠습니까?");
	        if (result==JOptionPane.OK_OPTION) {
	     	   dispose();
	     	   new MyPage(user);
	        }
		}  
	}
}