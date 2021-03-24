package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Movie.DB_MovieInfo;
import Movie.Movie;
import User.User;
import page.CategoryFrame.categoryEvent;

public class DOKPage extends CategoryFrame {
   //pos
   private final static int movieN = 4;
   
   //component
   private JPanel panel = new JPanel();
   private JLabel boxoffice = new JLabel();
   private JButton plusMovie = new JButton();
   private JLabel[] moviePoster = new JLabel[movieN];
   private ImageIcon[] imgPoster = new ImageIcon[movieN];
   private JLabel iconNew = new JLabel();
   private JLabel[] movieName = new JLabel[4];
   private Movie[] movies;
     
   //이미지
   private ImageIcon imgMore = new ImageIcon("src/imges/more.png");
   private ImageIcon imgBoxoffice = new ImageIcon("src/imges/boxoffice.png");
   private ImageIcon imgNew = new ImageIcon("src/img/new.png");
   
   //DB
   private DB_MovieInfo moive_connect = new DB_MovieInfo();
   
   //Font
   private Font font1 = new Font("나눔바른고딕", Font.PLAIN, 20); 
   private Font font2 = new Font("휴먼둥근헤드라인", Font.PLAIN, 35);
   private Font font3 = new Font("나눔바른고딕", Font.PLAIN, 25);
   
   public DOKPage(User user) {
      super("DOK");
      setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
      setResizable(false);
      //setDefaultCloseOperation(EXIT_ON_CLOSE);
      getContentPane().setLayout(null);//레이아웃 null
      setVisible(true);
      

   
      
      this.user = user;
      
      //만약 로그인 되어있다면
      if(user.getUserIsLogin()) {
         iconLogout.setBounds(1380, 20, 100, 30);
         iconLogout.setIcon(imgLogout);
         iconLogout.setOpaque(true);
         iconLogout.setBackground(Color.WHITE);
         iconLogout.setBorderPainted(false);
         iconLogout.addActionListener(new categoryEvent());
         topPanel.add(iconLogout);
         
      }else {
         //로그인
         iconLogin.setBounds(1275, 20, 100, 30);
         iconLogin.setIcon(imgLogin);
         iconLogin.setBackground(Color.red);
         iconLogin.setBorderPainted(false);
         iconLogin.setOpaque(true);
         iconLogin.setBackground(Color.WHITE);
         iconLogin.addActionListener(new categoryEvent());
         topPanel.add(iconLogin);
         
         //회원가입
         iconSignUp.setBounds(1380, 20, 100, 30);
         iconSignUp.setIcon(imgSignUp);
         iconSignUp.setBorderPainted(false);
         iconSignUp.setOpaque(true);
         iconSignUp.setBackground(Color.WHITE);
         iconSignUp.addActionListener(new categoryEvent());
         topPanel.add(iconSignUp);
      }
      
      //박스 오피스 Label
      boxoffice.setBounds(200,80,200,50);
      boxoffice.setIcon(imgBoxoffice);
      boxoffice.setOpaque(false);
      panel.add(boxoffice);
      
      //더 많은 영화 보기 Lable
      plusMovie.setIcon(imgMore);
      plusMovie.setBorderPainted(false);
      plusMovie.setOpaque(true);
      plusMovie.setBackground(Color.WHITE);
      plusMovie.setBounds(Main.SCREEN_WIDTH-350,120,200,50);
      plusMovie.addActionListener(new DOKListener());
      panel.add(plusMovie);
      
      movies = moive_connect.getMovieInfoAll("open_day");
      
      //영화 차트 버튼
      for(int i=0; i<movieN; i++) {
         int x = 300*(i)+ 180;
         String src = "src/imges/"+movies[i].get_key()+"H.jpg";
         //System.out.println(src);
         imgPoster[i] = new ImageIcon(src);
         //imgPoster[i] = new ImageIcon("src/imges/겨울 왕국.jpg");
         moviePoster[i] = new JLabel(imgPoster[i]);
         moviePoster[i].setIcon(imgPoster[i]);
         moviePoster[i].setBounds(x, 250, 255, 363);
         panel.add(moviePoster[i]);
      }
      
      iconNew.setIcon(imgNew);
      iconNew.setBounds(350, 195, 100, 50);
      iconNew.setOpaque(true);
      iconNew.setBackground(Color.WHITE);
      panel.add(iconNew);
      
      for(int i = 0; i < movieName.length; i++) {
         int x = 300*(i)+ 180;
         movieName[i] = new JLabel(movies[i].getM_name());
         movieName[i].setBounds(x, 650, 250, 30);
         movieName[i].setFont(font3);
         movieName[i].setHorizontalAlignment(JLabel.CENTER);
         panel.add(movieName[i]);
      }

      //Panel
      add(panel);
      panel.setBackground(Color.WHITE);
      panel.setBounds(0,(int) (Main.SCREEN_HEIGHT*0.25),Main.SCREEN_WIDTH,(int)(Main.SCREEN_HEIGHT*0.75));
      panel.setLayout(null);
      
   }
   
   class DOKListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         if(e.getSource() == plusMovie) {
            dispose();
            new ChartPage(user);
         }
      }
   }
}