import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


public class NameNode {

    // Block size of each fragment
    int blocksize;
    ArrayList<InetAddress> ips;
    int replication_factor;
    

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dfs";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn = null;
    Statement stmt = null;


    // Constructor
    public NameNode (int blocksize, int replication_factor) {
        this.blocksize = blocksize;
        this.replication_factor = replication_factor; 
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Convert File to array of Bytes
    public byte[] fileToBytes (File file) {
    	FileInputStream fis = null;
        try{
            byte[] bytearray = new byte[(int) file.length()];
            fis  = new FileInputStream(file);
            fis.read(bytearray);
            return bytearray;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
        	try{
        		fis.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
    }

    // Write array of Bytes to File
    public File bytesToFile (String name, byte[] bytearray) {
    	FileOutputStream fos = null;
        try {
            File file = new File(name);
            fos = new FileOutputStream(file);
            fos.write(bytearray);
            return file;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
        	try {
        		fos.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
    }

    // Divide file into fragments
    public Fragment[] fragmentFile (File file, String username) {

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
            fragments[i] = new Fragment(filename, bytes, i, username);
            index += blocksize;
        }

        bytes = Arrays.copyOfRange(bytearray, index, bytearray.length);
        fragments[i] = new Fragment(filename, bytes, i, username);

        return fragments;
    }
    
    public boolean defragmentFile (String filename, ArrayList<Fragment> fragments) {
    	boolean status = false;
    	byte[] bytes = null;
    	ByteBuffer buffer = null;
    	long size = 0;
    	String filepath = "C:/apache-tomcat-8.5.29/webapps/dfs/WEB-INF/downloads/";
    	
    	try {
    		for (Fragment fragment : fragments){
                size += fragment.size;
            }
    		
    		bytes = new byte[(int)size];
    		buffer = ByteBuffer.wrap(bytes);
    		
            for (Fragment fragment : fragments){
                buffer.put(fragment.getBytes());
            }
            
            FileOutputStream fos = new FileOutputStream(filepath + filename);
            fos.write(bytes);
            fos.close();
            status = true;
            
    	} catch (Exception e) {
    		status = false;
    	}
        return status;
    }

    public void sendFragment (String ip, Fragment fragment) {


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
        	udp.stop();
        	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void distributeFile (File file, String username) {
    	updateIPs();
    	Fragment[] fragments = fragmentFile(file, username);
    	int index = 0;
    	
    	for (int i = 0; i < fragments.length; i++) {
    		for (int j = 0; j < replication_factor; j++) {
    			sendFragment(ips.get(index).getHostAddress(), fragments[i]);
    			
    			System.out.println(i + " " + j);
    			
    			savetodatabase(username, file.getName(), fragments[i].seqno, ips.get(index).getHostAddress());
    			
    			index += 1;
    			index = index%ips.size();
    		}
    	}
    }
    
    public void collectFile (File file, String username) {
    	updateIPs();
    	ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    	DataNodeInterface node;
    	String filename = file.getName();
    	String sql = "select * from chunks where filename = '"+ filename + "' and username = '" + username + "' order by seqno";
    	
    	System.out.println(sql);
    	
    	int remotePort = 8983;
    	int seqno = -1;
    	String ip;
    	String fragment_filename = "";
    	int count;
    	String sql1;
    	
    	try{
    		sql1 = "select max(seqno) from chunks where filename = '" + filename + "'";
    		stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(sql1);
    		rs.next();
    		count = rs.getInt(1);
    		count++;
    		
    		stmt = conn.createStatement();
        	rs = stmt.executeQuery(sql);
        	int newseq = -1;
        	
        	while (rs.next()) {
            	ip = rs.getString("ip");
            	seqno = rs.getInt("seqno");
            	if (ips.contains(InetAddress.getByName(ip)) && newseq != seqno) {
            		String connectLocation = "//" + ip + ":" + remotePort + "/dfs";
            		node = (DataNodeInterface) Naming.lookup(connectLocation);
            		fragment_filename = username + "_" + filename + "_" + seqno + ".dfs";
            		fragments.add(node.pull(fragment_filename));
            		newseq = seqno;
            		count--;
            	}
        	}
        	
        	if (count == 0) {
        		defragmentFile (filename, fragments);
        	} else {
        		System.out.println("File cannot be created because one or more fragments are missing");
        	}
        	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void deleteFile (String filename, String username) {
    	try {
    		String select_query = "select id, ip, seqno from chunks where filename = '" + filename + "' and username = '" + username + "'";
    		
    		stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(select_query);
    		
    		while (rs.next()) {
    			
    			String ip = rs.getString("ip");
    			int seqno = rs.getInt("seqno");
    			
    			int remotePort = 8983;
            	String connectLocation = "//" + ip + ":" + remotePort + "/dfs";
            	DataNodeInterface node = (DataNodeInterface) Naming.lookup(connectLocation);
            	String fragment_filename = username + "_" + filename + "_" + seqno + ".dfs";
            	
            	System.out.println(fragment_filename);
            	
            	node.delete(fragment_filename);
    		}
    		
    		stmt = conn.createStatement();
        	String delete_query = "delete from chunks where filename = '" + filename + "' and username = '" + username + "'";
        	stmt.executeUpdate(delete_query);
        	stmt.close();
        	
    	} catch (Exception e) {
    		
    	}
    }
    
    public ArrayList<String> getFiles (String username) {
    
		ArrayList<String> files = new ArrayList<String>();
		
    	try {
    		String select_query = "select distinct filename from chunks WHERE username = " + "'"+ username + "'";
        	stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(select_query);
    		
    		while (rs.next()) {
    			String filename = rs.getString(1);
    			files.add(filename);
    		}
    		
    	} catch (Exception e) {
    		
		}
    	
		return files;
    }

	public void savetodatabase(String username, String filename, int seqno, String hostAddress) {
		try {
            
            PreparedStatement stmt = conn.prepareStatement("insert into chunks (filename, username, seqno, ip) values(?,?,?,?)");
            
            stmt.setString(1, filename);
            stmt.setString(2, username);
            stmt.setInt(3, seqno);
            stmt.setString(4, hostAddress);
            stmt.execute();            

            stmt.close();
            // conn.close();


        } catch (Exception e) {
        	e.printStackTrace();
        }

	}
}
