import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DataNodeClient {

	static String remoteHostName = "192.168.43.253";
    static int remotePort = 8983;
    static String connectLocation = "//" + remoteHostName + ":" + remotePort
            + "/dfs";
    
	public static void main(String[] args) {
		try{
			// Looking up the registry for the remote object
            DataNodeInterface node = (DataNodeInterface) Naming.lookup(connectLocation);
			// Getting the registry 
//	         Registry registry = LocateRegistry.getRegistry(null); 
	    
	         // Looking up the registry for the remote object 
//	         DataNodeInterface stub = (DataNodeInterface) registry.lookup("dfs"); 
             System.out.println(node.helloServer());
		
		}
		catch(Exception ae){
			ae.printStackTrace();
		}
	}

}
