package page;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Movie.DB_MovieInfo;
import Movie.Movie;
import User.User;


public class ChartPage extends CategoryFrame implements ActionListener, MouseListener{

   //pos
   private final static int Padding_Left = 130;
   private final static int Padding_Top = 50;
   private final static int right_Padding_Left = 25;
   private final static int right_Padding_Top = 30;
   private final static double Panel_Height = Main.SCREEN_HEIGHT * 1.4 ;
   
   // size
   private Dimension size = new Dimension();// 사이즈를 지정하기 위한 객체 생성
   
   //객체
   private DB_MovieInfo moive_connect = new DB_MovieInfo();
   private Movie[] movies;

   
   //component
   private JPanel panel = new JPanel();
   private JPanel selectPanel = new JPanel();
   private JLabel genre = new JLabel("장르");
   private String[] string_genre = {"전체", "공포", "드라마", "로맨스", "스릴러", "애니메이션", "액션", "SF"};
   //private JComboBox comboBox = new JComboBox(string_genre);
   private JButton rating = new JButton("평점");
   private JButton audience = new JButton("관객 수");
   private JButton openday = new JButton("개봉 날짜");
   private JButton ganada = new JButton("가나다 순");
   private JLabel[][] poster = new JLabel[2][4];
   private JLabel[][] movieInfo = new JLabel[2][4];
   private JLabel[] rank = new JLabel[8];
   private JLabel[] openDay = new JLabel[8];
   private JLabel[] raingNumber = new JLabel[8];
   private JLabel[] rankName = new JLabel[8];
   private ImageIcon iconPoster = new ImageIcon();
   
   private ButtonGroup g = new ButtonGroup();
   private JRadioButton[] radioGenre = new JRadioButton[8];
   
   //Design
   Font m_name_font = new Font("나눔바른고딕", Font.PLAIN, 20);
   Font m_rank_font = new Font("나눔바른고딕", Font.BOLD, 20);
   Font option_font = new Font("나눔바른고딕", Font.PLAIN, 15);
   Font fontMain = new Font("휴먼둥근헤드라인", Font.PLAIN, 25);
   Font fontContent = new Font("휴먼둥근헤드라인", Font.PLAIN, 17);
   Font movieInfoContentFont = new Font("나눔바른고딕", Font.PLAIN, 16);
   Color purple = new Color(82, 12, 139);

   public ChartPage() {
      
   }
   
   public ChartPage(User user) {
      super("영화 차트");
      setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
      setResizable(false);
      //setDefaultCloseOperation(EXIT_ON_CLOSE);
      getContentPane().setLayout(null);   //레이아웃 null
      setVisible(true);
      
      //영화 DB연결
      this.movies = moive_connect.getMovieInfoAll("rating");
      this.user = user;
      
      
      //장르
      genre.setBounds(right_Padding_Left, right_Padding_Top, 75, 40);
      genre.setFont(fontMain); genre.setHorizontalAlignment(JLabel.CENTER);
      selectPanel.add(genre);
      
      for(int i = 0; i < string_genre.length; i++) {
         radioGenre[i] = new JRadioButton(string_genre[i]);
         radioGenre[i].setBounds(right_Padding_Left + 25, right_Padding_Top + 50 + (i * 35), 100, 30);
         radioGenre[i].setOpaque(true);
         radioGenre[i].setBackground(Color.WHITE);
         radioGenre[i].setFont(option_font);
         radioGenre[i].addActionListener(this);
         g.add(radioGenre[i]);
         selectPanel.add(radioGenre[i]);
      }
      radioGenre[0].setSelected(true);
      
      //평점
      rating.setBounds(right_Padding_Left, right_Padding_Top + 375, 150, 40);
      rating.setBackground(Color.WHITE);
      rating.setFont(fontMain);
      rating.setHorizontalAlignment(JButton.LEFT);
      rating.setFocusPainted(false);
      rating.setBorderPainted(false);
      rating.addActionListener(this);
      selectPanel.add(rating);
      
      //관객 수
      audience.setBounds(right_Padding_Left, right_Padding_Top + 440, 150, 40);
      audience.setBackground(Color.WHITE);
      audience.setFont(fontMain);
      audience.setHorizontalAlignment(JButton.LEFT);
      audience.setFocusPainted(false);
      audience.setBorderPainted(false);
      audience.addActionListener(this);
      selectPanel.add(audience);
      
      //개봉날짜
      openday.setBounds(right_Padding_Left, right_Padding_Top + 505, 150, 40);
      openday.setBackground(Color.WHITE);
      openday.setFont(fontMain);
      openday.setHorizontalAlignment(JButton.LEFT);
      openday.setFocusPainted(false);
      openday.setBorderPainted(false);
      openday.addActionListener(this);
      selectPanel.add(openday);
   
      //가나다
      ganada.setBounds(right_Padding_Left, right_Padding_Top + 570, 150, 40);
      ganada.setBackground(Color.WHITE);
      ganada.setFont(fontMain);
      ganada.setHorizontalAlignment(JButton.LEFT);
      ganada.setFocusPainted(false);
      ganada.setBorderPainted(false);
      ganada.addActionListener(this);
      selectPanel.add(ganada);
      
      int n=0;
      //영화 포스터
      for(int i = 0; i < poster.length; i++) {
         for(int j = 0; j < poster[i].length; j++) {
            String src = "src/imges/"+movies[n].get_key()+".jpg";
            //String src = "src/imges/겨울 왕국.jpg";
            iconPoster = new ImageIcon(src);
            poster[i][j] = new JLabel(iconPoster);
            poster[i][j].setBounds(Padding_Left - 10 + (j * 260), Padding_Top + (i * 500), 219, 313);
            poster[i][j].setOpaque(true);
            poster[i][j].setBackground(Color.GRAY);
            poster[i][j].addMouseListener(this);
            panel.add(poster[i][j]);
            n++;
         }
      }
      
      n = 0;
      //영화 정보 출력(마우스 올렸을때)
        for(int i = 0; i < movieInfo.length; i++) {
           for(int j = 0; j <movieInfo[i].length; j++) {
              movieInfo[i][j] = new JLabel();
              movieInfo[i][j].setText("<html>개봉 : " + movies[n].getOpen_day() + "<br><br>등급 : " + movies[n].getGrade() + " 관람가" + "<br><br>장르 : " + movies[n].getGenre() + "<br><br>국가 : " + movies[n].getCountry() + "<br><br>러닝타임 : " + movies[n].getRunning_time() + "분" + "<br><br>배급 : " + movies[n].getDisributor());
              movieInfo[i][j].setBounds(Padding_Left - 10 + (j * 260), Padding_Top + (i * 500), 219, 313); 
              movieInfo[i][j].setOpaque(true);
              movieInfo[i][j].setBackground(Color.LIGHT_GRAY);
              movieInfo[i][j].setFont(movieInfoContentFont);
              movieInfo[i][j].addMouseListener(this);
              movieInfo[i][j].setVisible(false);
              panel.add(movieInfo[i][j]); 
              n++;
           }
       }
       
      for(int i = 0; i < rankName.length; i++) {
         rankName[i] = new JLabel();
         raingNumber[i] = new JLabel();
         openDay[i] = new JLabel();
         if(i < 4) {
            rankName[i].setBounds(5+Padding_Left - 10 + (i * 260), Padding_Top+330, 225, 40);
            raingNumber[i].setBounds(5+Padding_Left - 10 + (i * 260), Padding_Top+370, 60, 40);
            openDay[i].setBounds(65+Padding_Left - 10 + (i * 260), Padding_Top+370, 160, 40);
         }else {
            rankName[i].setBounds(10+Padding_Left -10 +((i-(rank.length/2)) * 260), Padding_Top+500+330 ,225, 40);
            raingNumber[i].setBounds(10+Padding_Left -10 +((i-(rank.length/2)) * 260), Padding_Top+500+370, 60, 40);
            openDay[i].setBounds(70+Padding_Left -10 +((i-(rank.length/2)) * 260), Padding_Top+500+370, 160, 40);
         }
         //이름
         rankName[i].setText((i+1)+". "+movies[i].getM_name());
         rankName[i].setFont(m_name_font);
         rankName[i].setForeground(purple);
         panel.add(rankName[i]);
         //평점
         raingNumber[i].setText("평점"+movies[i].getRating());
         raingNumber[i].setFont(option_font);
         panel.add(raingNumber[i]);
         //개봉일
         openDay[i].setText("  개봉일"+movies[i].getOpen_day());
         openDay[i].setFont(option_font);
         panel.add(openDay[i]);
         
      }
       
      
      //Panel_movie
      size.setSize(Main.SCREEN_WIDTH, Panel_Height);
      panel.setBackground(Color.WHITE);
      panel.setPreferredSize(size);
      panel.setLayout(null);
      
      //panel_select
      selectPanel.setBounds((int)(Main.SCREEN_WIDTH*0.8), (int) (Main.SCREEN_HEIGHT*0.25), (int)(Main.SCREEN_WIDTH*0.2), Main.SCREEN_HEIGHT);
      selectPanel.setBackground(Color.WHITE);
      selectPanel.setLayout(null);
      add(selectPanel);
      
      JScrollPane sp = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      sp.setBounds(0, (int) (Main.SCREEN_HEIGHT*0.25), (int)(Main.SCREEN_WIDTH*0.8), Main.SCREEN_HEIGHT);
      add(sp);
      
      
   }
   

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      String calumn = null;
      
      
      if(e.getSource() == rating) {
         calumn = "rating";
      }else if(e.getSource()==audience) {
         calumn = "audience";
      }else if(e.getSource()==openday) {
         calumn = "open_day";
      }else if(e.getSource()==ganada) {
         calumn ="m_name";
      }else {
         calumn = "rating";
      }
      
      if(radioGenre[0].isSelected() == true) {//all
         movies = moive_connect.getMovieInfoAll(calumn);
      }else {
         for(int i=1; i<string_genre.length; i++) {//나머지
            if(radioGenre[i].isSelected() == true) {
               movies = moive_connect.getMovieInfo(string_genre[i],calumn);
            }   
         }
      }
      
      
      
      //poster 이미지 set
      int n = 0;
      for(int i=0; i<poster.length; i++) {
         for(int j=0; j<poster[i].length; j++) {
            String src = "src/imges/"+movies[n].get_key()+".jpg";
            movieInfo[i][j].setText("<html>개봉 : " + movies[n].getOpen_day() + "<br><br>등급 : " + movies[n].getGrade() + " 관람가" + "<br><br>장르 : " + movies[n].getGenre() + "<br><br>국가 : " + movies[n].getCountry() + "<br><br>러닝타임 : " + movies[n].getRunning_time() + "분" + "<br><br>배급 : " + movies[n].getDisributor());
            //String src = "src/imges/겨울 왕국.jpg";
            iconPoster = new ImageIcon(src);
            poster[i][j].setIcon(iconPoster);
            n++;
         }
      }
      
  
      //영화 이름,영화 정보  set
      for(int i=0; i<rankName.length; i++) {
         rankName[i].setText((i+1)+". "+movies[i].getM_name());
         raingNumber[i].setText("평점"+movies[i].getRating());
         raingNumber[i].setFont(option_font);
         //개봉일
         openDay[i].setText("  개봉일"+movies[i].getOpen_day());
         openDay[i].setFont(option_font);
   
      }
       
   }//actionlistener

   @Override//때었다가 떨어지면?
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      System.out.println("mouseClicked"+e.getX()+","+e.getY());
   }

   @Override//마우스가 들어가면
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
   }

   @Override//마우스가 나가지면
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override//마우스가 눌리면
   public void mousePressed(MouseEvent e) { 
      // TODO Auto-generated method stub
      JLabel movielabel = (JLabel)e.getSource();
      for(int i = 0; i < poster.length; i++) {
         for(int j = 0; j < poster[i].length; j++) {
            if(e.getSource() == poster[i][j]) {
               poster[i][j].setVisible(false);
               movieInfo[i][j].setVisible(true);
            }else {
               poster[i][j].setVisible(true);
               movieInfo[i][j].setVisible(false);
            }
         }
      }
   }

   @Override//눌리는동안?
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub

   }
   
}