import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteFile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	NameNode node;

	public void init( ){
		// Get the file location where it would be stored.
		//filePath = getServletContext().getInitParameter("file-upload"); 
		node = new NameNode(1024, 2);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
		
		try {
			HttpSession session = request.getSession(false);
	    	
	    	String username = (String) session.getAttribute("username");
	    	
	    	String filename = request.getParameter("filename4");
	    	
	    	node.deleteFile(filename, username);
		} catch (Exception e) {
			
		} finally {
       	 RequestDispatcher rd=request.getRequestDispatcher("/");
         try {
			rd.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//method may be include or forward
     }
	}
	
}
