package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {
	private static final String SERVER_IP= "192.168.1.60";
	private static final int SERVER_PORT = 5050;

	public static void main(String[] args) {
		Socket socket =null;
		// TODO Auto-generated method stub
		//1. Socket 생성
		 
		
		//2. 서버 연결
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			
		//3.IOstream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os =socket.getOutputStream();
		
		//4. 쓰기/읽기
			while(true){
			String data = "hello\n";
			os.write(data.getBytes("utf-8"));
			
			
			byte[] buffer = new byte[256];
			int readCountByte =is.read(buffer);
			if(readCountByte<=-1)
			{
				System.out.println("[Client] disconnected by server");
				break;
			}
			
			data = new String (buffer,0,readCountByte,"utf-8");
			System.out.println("[client] received :"+data);
		}
		} catch (SocketException e)
		{
			//클라이언트가 소켓을 정상적으로 닫지 못하고 좋료한 경우
			System.out.println("[server] : closed by client");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			if(socket!=null && socket.isClosed()==false)
			{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
