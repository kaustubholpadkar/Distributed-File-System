import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class DownloadFile extends HttpServlet {
	
	
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
    	
    	OutputStream outStream;
    	try{
    	HttpSession session = request.getSession(false);
    	
    	String username = (String) session.getAttribute("username");
    	
    	String filename = request.getParameter("filename1");
    	// String filename = "twitter.png";
    	
    	File file = new File(filename);
    	node.collectFile(file, username);
    	
    	outStream = response.getOutputStream();

//        PrintWriter out = response.getWriter();    	
//        
//        out.println(filename + " " + username);

        String filepath = "C:/apache-tomcat-8.5.29/webapps/dfs/WEB-INF/downloads/";
        File download_file = new File(filepath + filename);
        
        response.setContentType("text/html");
        // response.setContentType("APPLICATION/PNG");
        response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
        
        FileInputStream fileInputStream = new FileInputStream(download_file);
        
        System.out.println("Not Expected!");
        
        ServletContext context = getServletContext();
     // gets MIME type of the file
        String mimeType = context.getMimeType(filename);
        
        response.setContentType(mimeType);
        response.setContentLength((int) download_file.length());
        
     // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", download_file.getName());
        response.setHeader(headerKey, headerValue);
        
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        /*
        int i;
        while ((i=fileInputStream.read()) != -1) {
            out.write(i);
        }

        out.close();
        
        */
        
        download_file.delete();
        
        fileInputStream.close();
        outStream.close();
    	}
    	catch (FileNotFoundException f) {
    		System.out.println("File cannot be created because one or more fragments are missing.............");
    		RequestDispatcher rd=request.getRequestDispatcher("/");
            try {
				rd.forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//method may be include or forward
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
