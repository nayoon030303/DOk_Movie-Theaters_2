import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
		Calendar calendar = Calendar.getInstance();
		/*today의 한달전*/
		calendar.add(Calendar.MONTH, -2);
		String today = sdf.format(calendar.getTime()).toString();
		
		System.out.println(today);
		
		/*Test*/
		ApiMovie apiMovie = new ApiMovie(today);
		apiMovie.start();
	}

}
