import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.rmi.Naming;
import java.util.ArrayList;

public class MainClass {
	
    public static void main (String[] args) {
    	
    	String base = "172.24.";
    	String url = null;
    	int remotePort = 8983;
    	ArrayList<String> active_datanodes = new ArrayList<String>();
    	
        try {
        	
        	for (int i = 13; i < 17; i++) {
        		for (int j = 0; j < 256; j++) {
            		url = base + i + "." + j;
                	try {
                		String connectLocation = "//" + url + ":" + remotePort + "/dfs";
                		// System.out.println(url);
                    	// DataNodeInterface node = (DataNodeInterface) Naming.lookup(connectLocation);
                		Naming.lookup(connectLocation);
                		System.out.println(url + "DONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    	active_datanodes.add(url);
                	} catch (Exception e) {
                		System.out.println(url + "NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                	}
            	}
        	}

        } catch (Exception e) {

        }

    }
}
