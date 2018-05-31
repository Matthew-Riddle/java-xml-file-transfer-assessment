package attempt2;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ClientHandler implements Runnable {

	Socket server;
	//FileOutputStream fout;
	//BufferedOutputStream ostream;
	
	BufferedInputStream buffIn;
	
	ClientHandler(Socket socket){
		this.server = socket;
	}
	
//	void writeFileToStream(Socket server) {
//		
//		File file = new File("borkCopy.jpg");
//		
//		try {
//			file.createNewFile();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		byte[] fileBuffer = new byte[(int)file.length()];
//		
//		try {
//			fout = new FileOutputStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//	
//		try {
//			ostream = new BufferedOutputStream(server.getOutputStream());
//		} catch (IOException e1) {
//
//			e1.printStackTrace();
//		}
//		
//		try {
//			fout.write(fileBuffer, 0, fileBuffer.length);
//			fout.flush();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		try {
//			ostream.write(fileBuffer, 0, fileBuffer.length);
//			ostream.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
	void unMarshal(Socket socket) {
		JAXBContext context;
		try {
			buffIn = new BufferedInputStream(socket.getInputStream());
			context = JAXBContext.newInstance(ClientData.class);
		
		
			Unmarshaller unmarshaller = context.createUnmarshaller();
		
			ClientData clientData = (ClientData) unmarshaller.unmarshal(new FileInputStream(new File("clientData.xml")));
			FileInputStream fstream = new FileInputStream(clientData.getFile());
			File file = new File(clientData.getFile(), "borkCopy.jpg");
			
			//Path path;
			//path = Paths.get("borkCopy.jpg");
			
			
			
			
		
			
			if(!file.createNewFile()) {
				file.mkdir();
				file.createNewFile();
			}
				
			
			
			fstream.close();
			
		} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void recieveFile(Socket socket) {
		
		try {
			buffIn = new BufferedInputStream(socket.getInputStream());
			
			//File file = new File();
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	



	@Override
	public void run() {
		unMarshal(server);
		try {
			buffIn.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}

}

