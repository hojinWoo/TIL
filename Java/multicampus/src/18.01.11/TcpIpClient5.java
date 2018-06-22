package day15;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;

public class TcpIpClient5 {

	public static void main(String[] args) {
		try {
			String serverIp = "127.0.0.1";
			Socket socket = new Socket(serverIp, 7777);
			System.out.println("서버연결되었습니다" );
			// 소켓의 입력스트림을 얻는다.
			Sender sender= new Sender(socket);
			Receiver receiver= new Receiver(socket);
			sender.start();
			receiver.start();
		
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
