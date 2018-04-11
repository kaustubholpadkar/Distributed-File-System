import java.io.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class DownloadDataNodeSetup extends HttpServlet {  
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		String filename = "dfs.zip";   
		String filepath = "C:/apache-tomcat-8.5.29/webapps/dfs/WEB-INF/setup/";   
		response.setContentType("APPLICATION/OCTET-STREAM");   
		response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
  
		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
            
		int i;   
		while ((i=fileInputStream.read()) != -1) {  
			out.write(i);   
		}   
		fileInputStream.close();
		out.close();   
	}  
}  