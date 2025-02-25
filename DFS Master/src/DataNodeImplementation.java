import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DataNodeImplementation extends UnicastRemoteObject implements DataNodeInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected DataNodeImplementation() throws RemoteException {
		super();
	}

	@Override
	public boolean push(String filename, Fragment fragment) throws RemoteException {
		
		boolean status = false;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
	
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(fragment);
			status = true;
			fos.close();
			oos.close();
			
		} catch (Exception e) {
			status = false;
		}
		
		return status;
	}

	@Override
	public Fragment pull(String filename) throws RemoteException {
		
		Fragment fragment = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		File file = null;
		
		try {
			file = new File(filename);
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			fragment = (Fragment) ois.readObject();
			ois.close();
			fis.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return fragment;
	}
	
	public boolean delete (String filename)  throws RemoteException {
		
		try {
			File file = new File(filename);
			file.delete();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
		
	} 

	@Override
	public String helloServer() throws RemoteException{
		// TODO Auto-generated method stub
				return "HelloWorld!";
	}

	@Override
	public boolean isLive() throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}
	
}
