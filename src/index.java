import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;

public class index {
    public static void main(String[] args) {
		new ServerThread().start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        String path = null; 
        System.out.println("Enter your filepath:"); 
        try {
			path = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        System.out.println("your path is :"+ path);
        
        File file = new File(path);
        System.out.println(file.exists()); //judge whether the file exist, if not, print "false".
       
        int i = path.indexOf(".");
        String post = path.substring(i + 1);
        if (!post.equals("xml")) {
           System.out.println("The file format is incorrect. Please select the xml format file.");
        } //Judge whether the path is in XML format， if not, print the sentence.
        
        upload up = new upload();
        String json = "";
        try {
        	
			json = up.upload(path);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("JSON: ");
        System.out.println(json);
        System.out.println("JSON FINISH");
        
        int port = 8000;
        String s1 = Client.sendPost("http://localhost:"+port, json);
        
        System.out.println("get page:");
        System.out.println(s1);
        
	}
}
