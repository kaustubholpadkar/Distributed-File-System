import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiClient {

	public static void main(String[] args) {
		
		try {
            String grp = "224.0.0.0";
            String server_ip = "172.24.13.11";
            int serverPort = 8899;

            MulticastSocket socket = new MulticastSocket(serverPort);
            socket.joinGroup(InetAddress.getByName(grp));

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
            //byte[] addr = server_ip.getBytes();
            DatagramPacket reply = new DatagramPacket(buf1, buf1.length, packet.getAddress(), serverPort);
            socket.send(reply);

            socket.leaveGroup(InetAddress.getByName(grp));
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
