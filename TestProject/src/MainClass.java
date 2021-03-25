import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainClass {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
		Calendar calendar = Calendar.getInstance();
		/*today의 한달전*/
		calendar.add(Calendar.MONTH, -1);
		String today = sdf.format(calendar.getTime()).toString();
		
		/*Test*/
		System.out.println(today);
		//ApiMovie apiMovie = new ApiMovie(today);
		String code = "20200703";
		APIMovie_info apiMovie = new APIMovie_info(code);
		apiMovie.start();
	}

}
