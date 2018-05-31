package attempt2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sun.xml.internal.messaging.saaj.util.Base64;


public class Client {

	Socket socket;
	private int port = 13482;
	private String ip = "localhost";
	
	BufferedInputStream buffInput;
	BufferedOutputStream buffOutput;
	
	Client(){
		
		try {
			
			socket = new Socket(ip, port);
			ClientData clientData = new ClientData();
			
			Path path = Paths.get("bork.jpg");
			
			
			File file = path.toFile();
			System.out.println(path.toAbsolutePath());
			
			
			//byte[] buffer = new byte[(int)file.length()];
			
			String date = LocalDateTime.now().getYear() + "/" + LocalDateTime.now().getMonth() + "/" + LocalDateTime.now().getDayOfMonth();
			
			//buffer = Base64.encode(Files.readAllBytes(path));
			
			//String encodedstring = encodeFileToBase64Binary(file);
			
			//System.out.println(encodedstring);
			
			buffOutput = new BufferedOutputStream(socket.getOutputStream());
			clientData.setName("Matthew");
			clientData.setDate(date);
			clientData.setFile(file); //This was a last minute change just to make the code run, not correct
			//clientData.setFilebytes(buffer);
			
			JAXBContext context = JAXBContext.newInstance(ClientData.class);
			
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(clientData, new File("clientData.xml"));
			
			System.out.println("finished marshalling");
			marshaller.marshal(clientData, buffOutput);
			System.out.println("finished marshalling again");
			
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	private static String encodeFileToBase64Binary(File file){
//        String encodedfile = null;
//        try {
//            FileInputStream fileInputStreamReader = new FileInputStream(file);
//            byte[] bytes = new byte[(int)file.length()];
//            fileInputStreamReader.read(bytes);
//            encodedfile = new String(Base64.encode(bytes), "UTF-8");
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return encodedfile;
//    }
	
	public static void main(String[] args) {
		new Client();
	}
	
}
