package API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Movie.Movie;

public class API_DailyBoxOffice extends API  {
	
	private String today = null;
	private Movie[] movies = new Movie[8];
	public API_DailyBoxOffice() {}
	public API_DailyBoxOffice(String today) {
		this.today = today;
		setApiURL("https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json");
	}
	
	@Override
	public void main() {
		// TODO Auto-generated method stub
		 String url = getApiURL()+"?key="+getKey()+"&targetDt="+today+"&itemPerPage=8";
	     String responseBody = get(url);
	     parseData(responseBody);
	}

	
	public void parseData(String responseBody) {
		
    	
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseBody.toString());
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("boxOfficeResult");
            JSONArray jsonArray = jsonObject1.getJSONArray("dailyBoxOfficeList");
            //jsonArray = jsonObject.getJSONArray("")

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                movies[i] = new Movie();
                movies[i].setM_name(item.getString("movieNm")); 
                movies[i].set_key(item.getString("movieNm"));
                movies[i].setOpen_day(item.getString("openDt"));
                movies[i].setAudience(Integer.parseInt(item.getString("audiAcc")));
                //System.out.println(movies[i].getM_name());
            }
           
        } catch (JSONException e) {
            e.printStackTrace();
        }
				
	}

	/*
	 * @Override public void run() { // TODO Auto-generated method stub main(); }
	 */
	public void start() {
		main();
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public Movie[] getMovies() {
		return movies;
	}
	public void setMovies(Movie[] movies) {
		this.movies = movies;
	}
	
	
}
