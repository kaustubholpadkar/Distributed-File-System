import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataNodeInterface extends Remote{
	public boolean push (String filename, Fragment file) throws RemoteException;
	
	public Fragment pull (String filename) throws RemoteException;
	
	public String helloServer() throws RemoteException;
	
	public boolean delete (String filename)  throws RemoteException;
	
	public boolean isLive () throws RemoteException;
}
