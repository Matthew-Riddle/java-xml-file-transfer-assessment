package attempt2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	
	ServerSocket server;
	
	
	private int port = 13482;
	
	Server(){}
	
	public void launch() {
		
		try {
			server = new ServerSocket(port);
			
			while(true) {
				
				
			Socket client = server.accept();
			System.out.println("New Client Connected");
			Thread thread = new Thread(new ClientHandler(client));
			thread.start();
			
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.launch();
	}
	
}
