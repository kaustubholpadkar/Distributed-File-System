import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.SliderUI;

public class NameNode {

    // Block size of each fragment
    int blocksize;
    ArrayList<InetAddress> ips;

    // Constructor
    public NameNode (int blocksize) {
        this.blocksize = blocksize;
    }

    // Convert File to array of Bytes
    public byte[] fileToBytes (File file) {
        try{
            byte[] bytearray = new byte[(int) file.length()];
            FileInputStream fis  = new FileInputStream(file);
            fis.read(bytearray);
            return bytearray;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Write array of Bytes to File
    public File bytesToFile (String name, byte[] bytearray) {
        try {
            File file = new File(name);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytearray);
            return file;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Divide file into fragments
    public Fragment[] fragmentFile (File file) {

        int numberOfFragments;
        byte[] bytes;
        String filename = file.getName();

        byte[] bytearray = fileToBytes(file);

        numberOfFragments = ((int) file.length()) / blocksize;
        if (file.length() % blocksize != 0)
            numberOfFragments++;

        Fragment[] fragments = new Fragment[numberOfFragments];

        int index = 0, i = 0;
        for (i = 0; i < numberOfFragments - 1; i++){
            bytes = Arrays.copyOfRange(bytearray, index, index + blocksize);
            fragments[i] = new Fragment(filename, bytes, i);
            index += blocksize;
        }

        bytes = Arrays.copyOfRange(bytearray, index, bytearray.length);
        fragments[i] = new Fragment(filename, bytes, i);

        return fragments;
    }
    
    public boolean defragmentFile (String filename, Fragment[] fragments) {
    	boolean status = false;
    	byte[] bytes = null;
    	ByteBuffer buffer = null;
    	long size = 0;
    	
    	try {
    		for (Fragment fragment : fragments){
                size += fragment.size;
            }
    		
    		bytes = new byte[(int)size];
    		buffer = ByteBuffer.wrap(bytes);
    		
            for (Fragment fragment : fragments){
                buffer.put(fragment.getBytes());
            }
            
            FileOutputStream fos = new FileOutputStream(filename);
            fos.write(bytes);
            fos.close();
            status = true;
            
    	} catch (Exception e) {
    		status = false;
    	}
        return status;
    }

    public void distribute (String ip, Fragment fragment) {


        try{
        	int remotePort = 8983;
        	String connectLocation = "//" + ip + ":" + remotePort + "/dfs";
            DataNodeInterface node = (DataNodeInterface) Naming.lookup(connectLocation);

//            System.out.println(node.helloServer());
            node.push(fragment.getFragment_filename(), fragment);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateIPs () {
    	try {
    		UDP_SERVER_THREAD udp = new UDP_SERVER_THREAD(9132);
        	
			Thread.sleep(1000);
    		
    		String grp = "224.0.0.0";
            int serverPort = 8899;
            
            MulticastSocket socket = new MulticastSocket();

            String msg = "Name Node is ON!";
            byte[] buf = msg.getBytes();

            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(grp), serverPort);

            socket.send(packet);
            socket.close();
    		udp.thread.join(5000);
        	ips = new ArrayList<InetAddress>(udp.ips);
        	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }

}
