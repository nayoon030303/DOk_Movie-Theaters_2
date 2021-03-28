package page;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Movie.DB_MovieTimeTable;
import Movie.MovieTimtTable;
import User.User;
import page.CategoryFrame;
import page.Main;
import page.ReservationCheckPage;
import reservation.Reservation;

public class MovieSitPage extends CategoryFrame implements Runnable {
	private final static int PaddingLeft = 150;
	private final static int PaddingTop = 125;

	final static int NO_SELECT = 0;
	final static int PRE_SELECT = 1;
	final static int NOW_SELECT = 2;

	// component
	private JPanel panel = new JPanel();
	private JLabel screen = new JLabel("SCREEN");

	// �¼�
	public static JButton[][] sit = new JButton[9][24];
	private int[][] int_selectedSit = new int[9][24];// ���õ� �ڼ��� 1

	private JButton gray = new JButton(); // gray�� ��ư
	private String[] str_number = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
	private JLabel adult = new JLabel("����");
	private JComboBox comboboxAdult = new JComboBox(str_number);
	private JLabel teen = new JLabel("û�ҳ�");
	private JComboBox comboboxTeen = new JComboBox(str_number);
	private JLabel kids = new JLabel("���");
	private JComboBox comboboxKids = new JComboBox(str_number);
	private JButton next = new JButton();
	private JLabel[] row = new JLabel[24];
	private JLabel[] column = new JLabel[9];
	private JLabel[] selectRow = new JLabel[24];
	private JLabel[] selectColumn = new JLabel[9];
	private Vector<String> seatName = new Vector<String>();

	private ImageIcon imgNext = new ImageIcon("src/imges/next.png");

	private int num_adult = 0;
	private int num_teen = 0;
	private int num_kids = 0;
	private String select = "";
	private int count = 0;
	private int selectCount = 0;
	private boolean isCountStart = false;
	private MovieTimtTable movieTimetable;
	private Reservation reservation = new Reservation();
	private String seatState;
	private String movie_yymmdd;
	private MovieTimtTable pre_MovieArea;
	
	// Design
	private Font sit_font = new Font("�����ٸ����", Font.BOLD, 15);
	private Font people = new Font("�����ٸ����", Font.PLAIN, 25);

	// DB
	private DB_MovieTimeTable connect_movieArea = new DB_MovieTimeTable();

	public MovieSitPage(User user, MovieTimtTable pre_movieArea, String movie_yymmdd) {
		super("��ȭ �¼� ����");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); // ���̾ƿ� null
		setVisible(true);

		startRunMovieSit = true;
		
		// ���� ����
		this.pre_MovieArea = pre_MovieArea;
		this.movie_yymmdd = movie_yymmdd;
		this.user = user;
		this.movieTimetable = connect_movieArea.getMovieArea(pre_movieArea.get_key());
		seatState = movieTimetable.getSeatState();

		// reservation �� ���� set
		reservation.setUserID(user.getUserID());
		reservation.setMovieareaKey(movieTimetable.get_key());
		reservation.setMoiveYYMMDD(movie_yymmdd);
		
		gray.setBackground(Color.LIGHT_GRAY);

		// ��ȭ �¼� ����	 ����1 ����ִ��ڸ� 0	
		int_selectedSit = setReservationSeat(seatState, int_selectedSit);
		
		
		// panel
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, (int) (Main.SCREEN_HEIGHT * 0.25), Main.SCREEN_WIDTH, (int) (Main.SCREEN_HEIGHT * 0.75));
		panel.setLayout(null);
		add(panel);

		screen.setBounds(PaddingLeft + 100, PaddingTop - 75, 1000, 40);
		screen.setOpaque(true);
		screen.setBackground(Color.LIGHT_GRAY);
		screen.setFont(people);
		screen.setHorizontalAlignment(JLabel.CENTER);
		panel.add(screen);

		// ����
		for (int i = 0; i < row.length; i++) {
			row[i] = new JLabel((i + 1) + "");

			if (i < 4) {
				row[i].setBounds(PaddingLeft + (i * 42), PaddingTop + 455, 40, 40);
			} else if (i < 20) {
				row[i].setBounds(PaddingLeft + 100 + (i * 42), PaddingTop + 455, 40, 40);
			} else {
				row[i].setBounds(PaddingLeft + 200 + (i * 42), PaddingTop + 455, 40, 40);
			}

			row[i].setFont(sit_font);
			row[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(row[i]);
		}

		column[0] = new JLabel("A");
		column[1] = new JLabel("B");
		column[2] = new JLabel("C");
		column[3] = new JLabel("D");
		column[4] = new JLabel("E");
		column[5] = new JLabel("F");
		column[6] = new JLabel("G");
		column[7] = new JLabel("H");
		column[8] = new JLabel("I");

		// ����
		for (int i = 0; i < column.length; i++) {
			column[i].setBounds(PaddingLeft - 50, PaddingTop + (i * 50), 40, 40);
			column[i].setFont(sit_font);
			column[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(column[i]);
		}

		// �¼�
		for (int i = 0; i < sit.length; i++) {
			for (int j = 0; j < sit[i].length; j++) {
				sit[i][j] = new JButton();

				if (j < 4) {
					sit[i][j].setBounds(PaddingLeft + (j * 42), PaddingTop + (i * 50), 40, 40);
				} else if (j < 20) {
					sit[i][j].setBounds(PaddingLeft + 100 + (j * 42), PaddingTop + (i * 50), 40, 40);
				} else {
					sit[i][j].setBounds(PaddingLeft + 200 + (j * 42), PaddingTop + (i * 50), 40, 40);
				}

				if (int_selectedSit[i][j] == 1) {// ����� �¼��� ������
					sit[i][j].setBackground(Color.BLACK);
					sit[i][j].setEnabled(false);
				} else {
					sit[i][j].setBackground(Color.LIGHT_GRAY);
					sit[i][j].addActionListener(new BtnEvent());
				}

				panel.add(sit[i][j]);
			}
		}

		adult.setBounds(PaddingLeft + 25, PaddingTop + 525, 60, 40);
		adult.setFont(people);
		adult.setHorizontalAlignment(JLabel.CENTER);
		panel.add(adult);

		comboboxAdult.setBounds(PaddingLeft + 100, PaddingTop + 525, 150, 30);
		comboboxAdult.setFont(sit_font);
		panel.add(comboboxAdult);

		teen.setBounds(PaddingLeft + 275, PaddingTop + 525, 150, 40);
		teen.setFont(people);
		teen.setHorizontalAlignment(JLabel.CENTER);
		panel.add(teen);

		comboboxTeen.setBounds(PaddingLeft + 400, PaddingTop + 525, 150, 30);
		comboboxTeen.setFont(sit_font);
		panel.add(comboboxTeen);

		kids.setBounds(PaddingLeft + 560, PaddingTop + 525, 150, 40);
		kids.setFont(people);
		kids.setHorizontalAlignment(JLabel.CENTER);
		panel.add(kids);

		comboboxKids.setBounds(PaddingLeft + 700, PaddingTop + 525, 150, 30);
		comboboxKids.setFont(sit_font);
		panel.add(comboboxKids);

		next.setBounds(PaddingLeft + 1100, PaddingTop + 525, 150, 50);
		next.setIcon(imgNext);
		next.setBorderPainted(false);
		next.addActionListener(new BtnEvent());
		next.setEnabled(false);
		panel.add(next);

	}

	class BtnEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			seatState = "";// �ʱ�ȭ
			for (int i = 0; i < sit.length; i++) {
				for (int j = 0; j < sit[i].length; j++) {
					if (e.getSource() == sit[i][j] && (sit[i][j].getBackground() == gray.getBackground())) {// ȸ���¼���
						
						if (num_adult == 0 && num_teen == 0 && num_kids == 0) {// ���ڸ� �������� �ʾ��� ��
							JOptionPane.showMessageDialog(null, "�ο��� �������ּ���");
						} else if (count <= selectCount) {// �¼����� �Ѿ ��
							JOptionPane.showMessageDialog(null, "�� �̻� �����Ͻ� �� �����ϴ�.");
						} else {
							sit[i][j].setBackground(new Color(82, 12, 139));// �����
							int_selectedSit[i][j] = NOW_SELECT;

							switch (i) {
							case 0:
								select = "A" + (j + 1);
								break;
							case 1:
								select = "B" + (j + 1);
								break;
							case 2:
								select = "C" + (j + 1);
								break;
							case 3:
								select = "D" + (j + 1);
								break;
							case 4:
								select = "E" + (j + 1);
								break;
							case 5:
								select = "F" + (j + 1);
								break;
							case 6:
								select = "G" + (j + 1);
								break;
							case 7:
								select = "H" + (j + 1);
								break;
							case 8:
								select = "I" + (j + 1);
								break;
							}
							seatName.add(select);
							selectCount++;

							/*
							 * if (selectCount == count) { next.setEnabled(true); }
							 */

						}
					} else if (e.getSource() == sit[i][j]) {// �����¼��� ����
						int_selectedSit[i][j] = NO_SELECT;// 0
						selectCount -= 1;
						seatName.remove(selectCount);
						sit[i][j].setBackground(Color.LIGHT_GRAY);

					}
					seatState += int_selectedSit[i][j];
				}
				seatState += '/';
			}

		
			// ���� ��ư
			if (e.getSource() == next) {
				go_Resevation();
			}

		}

	}
	int[][] setReservationSeat(String cutStr,int[][] seeatSit) {
		StringTokenizer str = new StringTokenizer(cutStr, "/");
		String[] strI = new String[str.countTokens()];
		int n = 0;
		while (str.hasMoreElements()) {
			strI[n] = str.nextToken();
			n++;
		}
		// ����� �¼��� ǥ��
		for (int i = 0; i < 9; i++) {// 9
			String[] strArray = strI[i].split("");
			int c = 0;
			for (String s : strArray) {// 24
				if (s.equals("1")) {
					seeatSit[i][c] = PRE_SELECT;
				} else if (s.equals("0")) {
					seeatSit[i][c] = NO_SELECT;
				}
				c += 1;
			}
		}
		return seeatSit;
	}

	@Override
	public void run() {
		while (true) {
			if(startRunMovieSit) {
				num_adult = comboboxAdult.getSelectedIndex();
				num_teen = comboboxTeen.getSelectedIndex();
				num_kids = comboboxKids.getSelectedIndex();
				count = num_adult + num_teen + num_kids;
				if (selectCount == count && count != 0) {
					next.setEnabled(true);
				} else if (selectCount < count) {
					next.setEnabled(false);
				}

			}else {
				break;
			}

		}
		
	}
	
	public void go_Resevation() {
		if (num_adult == 0 && num_teen == 0 && num_kids == 0) {
			JOptionPane.showMessageDialog(null, "�ο��� �������ּ���");
		} else {
			// DB_MovieArea���� seatState �ޱ�
			String db_seatState = connect_movieArea.getMovieArea(movieTimetable.get_key()).getSeatState();
			int[][] intdb_seatSit = new int[9][24];
			
			//��ȭ �¼� ����	 ����1 ����ִ��ڸ� 0	
			intdb_seatSit = setReservationSeat(db_seatState, intdb_seatSit);
		
			int c = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 24; j++) {
					if (intdb_seatSit[i][j] == NO_SELECT && int_selectedSit[i][j] == NOW_SELECT) {
						c++;
					}
				}
			}
			
			// ���� ������
			if (c == count) {
				//seatState = seatState.replace("2", "1");// 2�� 1�� ġȯ
				String[] seat_Name = new String[seatName.size()];
				for(int i=0; i<seatName.size(); i++) {
					seat_Name[i] = (String)seatName.get(i);
				}
				Arrays.sort(seat_Name);//���� ��������
				// ������ ����
				reservation.setSeatWhere(Arrays.toString(seat_Name));
				reservation.setSeatCount(selectCount);
				movieTimetable.setSeatState(seatState);
				Thread t1 = new Thread(new PayPage(user, reservation, movieTimetable,num_adult,num_teen, num_kids));
				t1.start();
				startRunMovieSit = false;
			
			} else {
				JOptionPane.showMessageDialog(null, "�̹� ���õ� �¼��Դϴ�.");
				MovieSitPage t1 = new MovieSitPage(user, movieTimetable,movie_yymmdd);
				Thread th = new Thread(t1);
				th.start();
			}

			dispose();
		} // ���� ��
	}

}