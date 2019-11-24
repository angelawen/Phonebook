
public class ServerThread extends Thread {
    
    public void run() {
    	Server server = new Server();
    	try {
			server.server(8000);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
