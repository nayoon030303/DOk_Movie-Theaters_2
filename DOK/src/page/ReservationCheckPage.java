package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Movie.Movie;
import Movie.MovieTimtTable;
import User.User;
import page.CategoryFrame.windowAdapter;
import reservation.Reservation;

public class ReservationCheckPage extends JFrame implements ActionListener, Runnable {
   private final static int PaddingLeft = 50;
   private final static int PaddingTop = 175;
   private final static int PRICE_Y = 50;
   
   //component
   private JPanel panel = new JPanel();
   private JLabel logo = new JLabel();
   private JLabel[] people = new JLabel[3];
   private JLabel[] peoplePrice = new JLabel[3];
   private JLabel result = new JLabel();
   private JLabel selectSit = new JLabel("�����Ͻ� �¼��Դϴ�.");
   private JLabel sit = new JLabel();
   private JLabel goContineu = new JLabel("������ �Ϸ�Ǿ����ϴ�.");
   private JButton sure = new JButton();
   
   Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("src/imges/p_octopus.png");
   
   //�̹���
   private ImageIcon imgSure = new ImageIcon("src/img/check.png");
   
   private Reservation ticket;
   private User user;
   private MovieTimtTable movieArea;
   int resultPrice;
   
   
   //font
   private Font Logo = new Font("Franklin Gothic Heavy", Font.PLAIN, 120);
   Font bold_font = new Font("�����ٸ����", Font.BOLD, 25);
   Font plain_font = new Font("�����ٸ����", Font.PLAIN, 20);
   Font result_font = new Font("�����ٸ����", Font.BOLD, 30);
   
   public ReservationCheckPage(User user,MovieTimtTable movieArea,Reservation ticket,int num_adult, int num_teen, int num_kids) {
      
	   setIconImage(img);
	   
      //�ݾ� ����
      int adultPrice = num_adult * Movie.ADULT;
      int teenPrice = num_teen * Movie.TEEN;
      int kidsPrice = num_kids * Movie.KIDS;
      resultPrice = (adultPrice + teenPrice + kidsPrice);
      
      //������ 
      this.user = user;
      this.ticket = ticket;
      this.movieArea = movieArea;
      
      // �ΰ� label
      logo.setText("DoK");
      logo.setFont(Logo);
      logo.setBounds(125, 50, 275, 100);
      panel.add(logo);
      
      //Price
      for(int i = 0; i < people.length; i++) {
         people[i] = new JLabel();
         people[i].setBounds(PaddingLeft, PaddingTop + (75 * i), 150, 50);
         people[i].setFont(bold_font);
         people[i].setHorizontalAlignment(JLabel.LEFT);
         panel.add(people[i]);
      
      }
      people[0].setText("���� " + num_adult + "��");
      people[1].setText("û�ҳ� " + num_teen + "��");
      people[2].setText("��� " + num_kids + "��");
      
      for(int i = 0; i < peoplePrice.length; i++) {
         peoplePrice[i] = new JLabel();
         peoplePrice[i].setBounds(PaddingLeft + 200, PaddingTop + (75 * i), 200, 50);
         peoplePrice[i].setFont(plain_font);
         peoplePrice[i].setHorizontalAlignment(JLabel.RIGHT);
         panel.add(peoplePrice[i]);
      
      }
      peoplePrice[0].setText(adultPrice + "��");
      peoplePrice[1].setText(teenPrice + "��");
      peoplePrice[2].setText(kidsPrice + "��");
      
      result.setText(resultPrice + "��");
      result.setBounds(PaddingLeft + 200, PaddingTop + 225, 200, 60);
      result.setFont(result_font);
      result.setHorizontalAlignment(JLabel.RIGHT);
      panel.add(result);
      
      //�¼� Ȯ��
      selectSit.setBounds(PaddingLeft, PaddingTop + 350, 250, 50);
      selectSit.setFont(bold_font);
      selectSit.setHorizontalAlignment(JLabel.LEFT);
      panel.add(selectSit);
      
      sit.setText(ticket.getSeatWhere());
      sit.setBounds(PaddingLeft, PaddingTop + 425, 400, 50);
      sit.setFont(plain_font);
      panel.add(sit);
      
      //���� �������� �̵�
      goContineu.setBounds(PaddingLeft + 1, PaddingTop + 525, 300, 50);
      goContineu.setFont(bold_font);
      goContineu.setHorizontalAlignment(JLabel.LEFT);
      panel.add(goContineu);
      
      sure.setIcon(imgSure);
      sure.setBounds(PaddingLeft + 125,PaddingTop + 600,150,50);
      sure.setBorderPainted(false);
      panel.add(sure);
      
      sure.addActionListener(this);
      
      
      add(panel);
      
      panel.setLayout(null);
      panel.setBackground(Color.WHITE);
      
      setTitle("������");
      setSize(500, 900);
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
      addWindowListener(new windowAdapter());
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == sure) {
    	  new DOKPage(user);
         dispose();
      }
   }
   class windowAdapter extends WindowAdapter{

      @Override
      public void windowClosing(WindowEvent e) {
           int result = JOptionPane.showConfirmDialog(null,"������ȭ���� �����Ͻðڽ��ϱ�?");
           if (result==JOptionPane.OK_OPTION) {
        	   new DOKPage(user);
              dispose();
              
           }
      }  
   }
   @Override
   public void run() {
      // TODO Auto-generated method stub
      
   }
   /*
    * public static void main(String[] args) { User user = new User(); Ticket
    * ticket = new Ticket(); MovieArea movieArea = new MovieArea(); Vector<String>
    * seat_name = new Vector<String>(); new ReservationCheckPage(user, 1, 1, 1,
    * seat_name, ticket, movieArea); }
    */
}