import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiClientThread implements Runnable{

	String grp = "224.0.0.0";
	int serverPort;
    int localPort = 9001;
    String server_ip;
    MulticastSocket socket;
    Thread thread;
    
    public MultiClientThread(String server_ip, int serverPort) {
    	thread = new Thread(this);
    	
    	this.serverPort = serverPort;
    	this.server_ip = server_ip;
    	
    	try {
    		socket = new MulticastSocket(serverPort);
            socket.joinGroup(InetAddress.getByName(grp));    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	thread.start();
    	
    }
	
	@Override
	public void run() {
		while (true) {
			try {
	            InetAddress localAddress = InetAddress.getLocalHost();
	            
	            int buf_size = 1024;
	            byte[] buf = new byte[buf_size];
	            String msg;

	            DatagramPacket packet = new DatagramPacket(buf, buf_size);
	            socket.receive(packet);

	            msg = new String(packet.getData());
	            msg = msg.trim();
	            System.out.println("Received: " + msg);
	            
	            msg = "Hii Server";
	            byte[] buf1 = msg.getBytes();
	            DatagramPacket reply = new DatagramPacket(buf1, buf1.length, InetAddress.getByName(server_ip), serverPort);
	            DatagramSocket mysocket = new DatagramSocket(localPort, localAddress);
	            mysocket.send(reply);
	            mysocket.close();

//	            socket.leaveGroup(InetAddress.getByName(grp));
//	            socket.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}

}
