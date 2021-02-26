package webStuff;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.Elements;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class WebTrigger {
	
	private List<String> cookies;
	private HttpsURLConnection conn;
	private static URL site;

	public static void main(String[] args) throws Exception {
		
		/*
		 * TODO: cannot get past login, see why login page not updating
		 */
		
		WebTrigger http = new WebTrigger();
		
		String url = "https://aternos.org/server/";
		String login = "https://aternos.org/go/";
		
		String userName = "document.getElementById('user').value = 'brainstriike'";
		String pass = "document.getElementById('password').value = '56E5NupEwKm4P34'";
		String loginClick = "document.getElementById('login').click()";
		String startServer = "document.getElementById('start').click()";
		
		site = new URL(login);
		
		http.sendPost(userName);
		http.sendPost(pass);
		http.sendPost(loginClick);
		
		site = new URL(url);
		
		http.sendPost(startServer);
		
	}
	
	private void sendPost(String postParam) throws Exception {
		
		URL obj = site;
		conn = (HttpsURLConnection) obj.openConnection();
		/*
		//act like a browser
		conn.setUseCaches(false);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Host", "https://aternos.org");
	    //conn.setRequestProperty("User-Agent", USER_AGENT);
	    conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	    //for (String cookie : this.cookies) {
	    //    conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
	    //}
	    conn.setRequestProperty("Connection", "keep-alive");
	    //conn.setRequestProperty("Referer", "https://accounts.google.com/ServiceLoginAuth");
	    //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setRequestProperty("Content-Length", Integer.toString(postParam.length()));
*/
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    
	    
	    //send post request
	    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	    wr.writeBytes(postParam);
	    wr.flush();
	    wr.close();

	    int responseCode = conn.getResponseCode();
	    //System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + postParam);
	    System.out.println("Response Code : " + responseCode);

	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
	        response.append(inputLine);
	    }
	    in.close();
	    System.out.println(response.toString());
	    
	    
	}
	
	

}
