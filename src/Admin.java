import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Admin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NameNode node;

	public void init( ){
		// Get the file location where it would be stored.
		//filePath = getServletContext().getInitParameter("file-upload"); 
		node = NameNode.getNode();
	}
	   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String str_repfactor = request.getParameter("replication_factor");
    	String str_blocksize = request.getParameter("blocksize");
    	int replication_factor, blocksize;
    	if (!str_repfactor.equals("") && str_repfactor != null) {
    		replication_factor = Integer.parseInt(str_repfactor);
    		node.setReplicationFactor(replication_factor);
    	}
    	
    	if (!str_blocksize.equals("") && str_blocksize != null) {
    		blocksize = Integer.parseInt(str_blocksize);
    		node.setBlockSize(blocksize);
    	}
    	
    	
    	RequestDispatcher rd=request.getRequestDispatcher("/AdminIndex");
        try {
			rd.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//method may be include or forward
    }
}
