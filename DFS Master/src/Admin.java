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
    	
    	int replication_factor = Integer.parseInt(request.getParameter("replication_factor"));
    	int blocksize = Integer.parseInt(request.getParameter("blocksize"));
    	
    	node.setBlockSize(blocksize);
    	node.setReplicationFactor(replication_factor);
    	
    	RequestDispatcher rd=request.getRequestDispatcher("/AdminIndex");
        try {
			rd.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//method may be include or forward
    }
}
