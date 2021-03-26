package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Movie.Movie;

abstract class API {
	private static final String KEY = "ca902f2712bf92fc02cbedd133e32606"; 
	private String apiURL = null;
	
	
	/*필요한 url을 만들어 넘긴다.*/
	public abstract void main();
	
	public static String get(String apiUrl) {
        String responseBody = null;
        try {
            URL url = new URL(apiUrl);
          
            
			/*url.openStream을 통해서 url주소를 InputStream에 넣어준다. 다양한 입출력 기능 가능*/
			java.io.InputStream in = url.openStream(); 
            responseBody = readBody(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody;
    }
	
	/* 요청받은 URL을 읽는 함수 */
	public static String readBody(java.io.InputStream in) throws UnsupportedEncodingException{
    	/*한글 인코딩을 해주어야 한글이 꺠지지 않는다*/
        InputStreamReader streamReader = new InputStreamReader(in,"UTF-8");

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	
  
	
    public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

	public static String getKey() {
		return KEY;
	}

}
