import java.io.File;

public class MainClass {
	
    public static void main (String[] args) {
    	
        try {
        	File file = new File(args[0]);
        	String username = args[1];
        	
        	NameNode namenode = NameNode.getNode();
        	// namenode.distributeFile(file, username);
        	namenode.collectFile(file, username);
        	
        } catch (Exception e) {

        }

    }
}
