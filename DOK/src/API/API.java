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
	
	
	/*�ʿ��� url�� ����� �ѱ��.*/
	public abstract void main();
	
	public static String get(String apiUrl) {
        String responseBody = null;
        try {
            URL url = new URL(apiUrl);
          
            
			/*url.openStream�� ���ؼ� url�ּҸ� InputStream�� �־��ش�. �پ��� ����� ��� ����*/
			java.io.InputStream in = url.openStream(); 
            responseBody = readBody(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody;
    }
	
	/* ��û���� URL�� �д� �Լ� */
	public static String readBody(java.io.InputStream in) throws UnsupportedEncodingException{
    	/*�ѱ� ���ڵ��� ���־�� �ѱ��� ������ �ʴ´�*/
        InputStreamReader streamReader = new InputStreamReader(in,"UTF-8");

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
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
