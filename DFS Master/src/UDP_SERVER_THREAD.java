import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class UDP_SERVER_THREAD implements Runnable{
	int port;
	Thread thread;
	ArrayList<InetAddress> ips;
	
	public UDP_SERVER_THREAD(int port){
		this.port = port;
		ips = new ArrayList<InetAddress>();
		thread = new Thread(this);
		thread.start();
	}
	
	public void run(){
		DatagramSocket serverSocket = null;
		try{
			InetAddress localAddress = InetAddress.getLocalHost();
	        InetAddress clientIp;

	        serverSocket = new DatagramSocket(port, localAddress);
	        DatagramPacket msg_received;

	        int buffer_size = 2000;
	        byte[] msg_receive_buffer;
	        int i = 0;
	        
	        for (i = 0; i < 2; i++) {

	            msg_receive_buffer = new byte[buffer_size];

	            msg_received = new DatagramPacket(msg_receive_buffer, buffer_size);
	            serverSocket.receive(msg_received);
	            clientIp = msg_received.getAddress();
	            System.out.println(clientIp.getHostAddress());
	            ips.add(clientIp);

	        }
	        
		} catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				serverSocket.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
