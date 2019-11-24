
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
 
 
/*
 * Java sends HTTP 'get' and 'post' requests.
 */
public class Client {
	public static String sendPost(String url, String param){
		String result = "";
		try{
			URL realUrl = new URL(url);
			//Open the connection to the URL.
			URLConnection conn =  realUrl.openConnection();
			//Set general request properties.
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//To send a POST request, you must set the following two lines.
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//Get the output stream corresponding to the URLConnection object.
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			//Send request parameter
			out.print(param);
			//Flush output stream buffer.
			out.flush();
			// Define the BufferedReader input stream to read the response of the URL.
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
		} catch (Exception e) {
			System.out.println("Sending a POST reques while an exception occurred." + e);
			e.printStackTrace();
		}
		return result;
	}
	
	//Test sends a POST request.
	public static void main(String[] args) throws Exception{
        //Send POST request.
        String s1 = Client.sendPost("http://localhost:8000", "testp");
        System.out.println(s1);
    }
}