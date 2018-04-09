import java.io.File;

public class MainClass {
	
    public static void main (String[] args) {
    	
        try {
        	File file = new File(args[0]);
        	String username = args[1];
        	
        	NameNode namenode = new NameNode(1024, 3);
        	namenode.distributeFile(file, username);
        	
        } catch (Exception e) {

        }

    }
}
