package server;

public class ServerThread extends Thread {
	int Port;
	
	ServerThread(int port){
		this.Port = port;
	}
    
    public void run() {
    	Server server = new Server();
    	try {
			server.server(this.Port);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
