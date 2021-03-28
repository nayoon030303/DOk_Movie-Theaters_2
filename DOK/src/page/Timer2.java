package page;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Movie.MovieTimtTable;
import User.User;
import reservation.Reservation;

class Timer2 extends Thread {
	private JFrame frame;
	private JLabel timerLabel;
	private int n;
	private User user;
	private MovieTimtTable movieArea;
	private Reservation ticket;
	private int num_adult, num_teen, num_kids;

	public Timer2(JFrame frame, JLabel timerLabel, User user, MovieTimtTable movieArea, Reservation ticket, int num_adult,
			int num_teen, int num_kids) {
		this.frame = frame;
		this.timerLabel = timerLabel;
		this.user = user;
		this.movieArea = movieArea;
		this.ticket = ticket;
		this.num_adult = num_adult;
		this.num_teen = num_teen;
		this.num_kids = num_kids;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		n = 5;
		while (!this.isInterrupted()) {
			n--;
			try {
				if (n < 0) {

					new ReservationCheckPage(user, movieArea, ticket, num_adult, num_teen, num_kids);
					frame.dispose();
					interrupt();

				}
				sleep(1000); // 1/1000ÃÊ ´ÜÀ§
			} catch (InterruptedException e) {
				// TODO: handle exception
				return;
			}
		}
	}

}