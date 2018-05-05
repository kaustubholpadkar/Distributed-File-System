import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiServer {

	public static void main(String[] args) {
		try {
            String grp = "224.0.0.0";
            int serverPort = 8899;

            MulticastSocket socket = new MulticastSocket();

            String msg = "Welcome Client";
            byte[] buf = msg.getBytes();

            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(grp), serverPort);

            socket.send(packet);
            
            for (int i = 0; i < 15; i++) {
            	DatagramPacket receivemsg = new DatagramPacket(buf, buf.length);
                socket.receive(receivemsg);
                System.out.println(receivemsg.getData());
                System.out.println(receivemsg.getAddress().toString());
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
