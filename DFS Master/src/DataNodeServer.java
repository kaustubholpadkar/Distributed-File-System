import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DataNodeServer {
	
	public DataNodeServer() {}

	public static void main(String[] args) {
		
		String server_ip = args[0];
		int serverPort = 9132;
		
		try {
			int port = 8983;
			DataNodeImplementation node = new DataNodeImplementation();
			LocateRegistry.createRegistry(port);
			String bindLocation = "//localhost:" + port + "/dfs";
			Naming.bind(bindLocation, node);
			System.out.println("Server ready...");
			
			MultiClientThread multiClientThread = new MultiClientThread(server_ip, serverPort);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
