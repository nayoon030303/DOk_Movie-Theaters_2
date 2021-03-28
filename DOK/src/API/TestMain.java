package API;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TestMain {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",Locale.KOREA);
		Calendar calendar = Calendar.getInstance();
		/*today의 한달전*/
		//calendar.add(Calendar.MONTH, -1);
		String today = sdf.format(calendar.getTime()).toString();
		
		/*Test*/
		System.out.println(today);
		//API_DailyBoxOffice apiMovie = new API_DailyBoxOffice(today);
		//Thread td = new Thread(apiMovie);
		//td.start();
		//String code = "20200703";
		//APIMovie_info apiMovie = new APIMovie_info(code);
	

	}

}
