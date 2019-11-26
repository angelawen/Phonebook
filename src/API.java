package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class API {
	public static void startServer(int port) {
		Thread server = new ServerThread(port);
		server.setName("server");
		System.out.println("Starting server thread...");
		server.start();
		//return 1;
	}
	
	public static String Client(String filepath, String url, int port) {
		Client client = new Client();
		String page = client.clientPost(filepath,url,port);
		return page;
	}
	
	
	
}
