package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) {
		int serverPort=0;
		int clientPort=0;
		String url=null;
		String filepath=null;
		
		API server = new API();
		
		//input server port
		BufferedReader br_port = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Enter your server port:"); 
        try {
			serverPort = Integer.parseInt(br_port.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} 
        System.out.println("your server port is :"+ serverPort);
        System.out.println("server start");
        
         server.startServer(serverPort);
        
        //input url
      	BufferedReader br_url = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Enter your url:"); 
        try {
        	url =br_url.readLine();
      	} catch (IOException e) {
      		e.printStackTrace();
      	} 
        //System.out.println("your url is :"+ serverPort);
        BufferedReader br_ClientPort = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Enter your client port:"); 
        try {
        	clientPort = Integer.parseInt(br_ClientPort.readLine());
      	} catch (IOException e) {
      		e.printStackTrace();
      	} 
        System.out.println("your url is :"+ url + ":" + clientPort);
        
        //input path
      	BufferedReader br_path = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Enter your filepath:"); 
        try {
        	filepath = br_path.readLine();
      	} catch (IOException e) {
      		e.printStackTrace();
      	} 
        System.out.println("your file path is :"+ filepath);
        
        String page = server.Client(filepath, url, clientPort);
        
        System.out.println("Client get page:");
        System.out.println(page);
        

	}
}
