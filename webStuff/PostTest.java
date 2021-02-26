package webStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;

public class PostTest {
	
	private URL url;
	private HttpURLConnection con;
	
	public PostTest() throws IOException, InterruptedException {
		url = new URL("https://aternos.org/go/");
		con = (HttpURLConnection) url.openConnection();
		
		post();
		
	}
	
	public void post () throws ProtocolException, UnsupportedEncodingException {
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		String username = "document.getElementById('user').value = 'brainstriike'";
		String password = "document.getElementById('password').value = '56E5NupEwKm4P34'";
		String log = "document.getElementById('login').click()";
		
		byte[] user = username.getBytes("utf-8");
		byte[] pass = username.getBytes("utf-8");
		byte[] login = log.getBytes("utf-8");
		
		send(user);
		send(pass);
		String success = send(login);
		
		System.out.println(success);
		
	}
	
	public String send(byte[] in) {
		//send post
		try(OutputStream os = con.getOutputStream()){
			byte[] input = in;
			os.write(input, 0, input.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//get response
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			return response.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "failed somewhere";
	}
	
	public void notWorking() {
		//client = HttpClient.newHttpClient();
		String login = "document.getElementById('user').value = 'brainstriike'";
		String pass ="document.getElementById('password').value = '56E5NupEwKm4P34'";
		String loginClick = "document.getElementById('login').click()";
		String toServer = "";
		String StartServer = "";
		
		
		HttpRequest request = (HttpRequest) HttpRequest.newBuilder(
				URI.create("https://aternos.org/go/")).POST(HttpRequest.BodyPublishers.ofString(login));
		
		//HttpResponse<Object> response = client.send(request, null);
	}
	
	
}
