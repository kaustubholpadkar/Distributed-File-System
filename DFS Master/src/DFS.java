import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DFS extends HttpServlet {
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
            throws ServletException, IOException {

		
		
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        
        ArrayList<String> files = node.getFiles(username);
        
        String html_files = "<table>";
        
        html_files += "<tr> <th> File Name </th> <th> Download </th> <th> Delete </th> </tr>";
        
        for (int i = 0; i < files.size(); i++) {
        	html_files += "<tr>";
        	html_files += "<td>" + files.get(i)+ "</td>";
        	html_files += "<td>" + "<a href = 'DownloadFile?filename1='" + files.get(i) + "'> Download </a>" + "</td>";
        	html_files += "<td>" + "<a href = 'DeleteFile?filename4='" + files.get(i) + "'> Delete </a>" + "</td>";
        	html_files += "</tr>";
        }
        html_files += "</table>";
        
        // String password = (String) session.getAttribute("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session == null) {
            RequestDispatcher rd=request.getRequestDispatcher("/");
            rd.forward(request, response);//method may be include or forward
        } else {

            out.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    \n" +
                    "    <head>\n" +
                    "        <meta charset=\"utf-8\">\n" +
                    "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "        <title>Index Servlet</title>\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        \n" +
                    "        <h1>\n" + 
                    username +
                    "		</h1>\n" +
                    "        \n" +
                    "        <h2>Download Setup</h2>\n" +
                    "        <form method = \"GET\" action = \"DownloadDataNodeSetup\" name=\"form1\">\n" +
                    "            <input type=\"submit\" value=\"download setup\">\n" +
                    "        </form>\n" +
                    "        \n" +
                    "        <hr>\n" +
                    "        \n" +
                    "        <h2>Upload File</h2>\n" +
                    "        <form method = \"POST\" action = \"UploadFile\" name=\"form2\" enctype = \"multipart/form-data\">\n" +
                    "            \n" +
                    "            <input type=\"file\" name=\"file\">\n" +
                    "            <br>\n" +
                    "            <input type=\"submit\" value=\"Upload File\">\n" +
                    "        </form>\n" +
                    "        \n" +
                    "        <hr>\n" +
                    "        \n" +
                    "        <h2>Download File</h2>\n" +
                    "        <form method = \"GET\" action = \"DownloadFile\" name=\"form3\">\n" +
                    "            Filename: <input type=\"text\" id=\"filename1\" name=\"filename1\">\n" +
                    "            <br>\n" +
                    "            <input type=\"submit\" value=\"Download File\">\n" +
                    "            <!--a href=\"DownloadFile\">Download File</a-->\n" +
                    "        </form>\n" +
                    "        <hr>\n" +
                    "        \n" +
                    "        <h2>Delete File</h2>\n" +
                    "        <form method = \"GET\" action = \"DeleteFile\" name=\"form4\">\n" +
                    "            Filename: <input type=\"text\" id=\"filename4\" name=\"filename4\">\n" +
                    "            <br>\n" +
                    "            <input type=\"submit\" value=\"Delete File\">\n" +
                    "            <!--a href=\"DownloadFile\">Download File</a-->\n" +
                    "        </form>\n" +
                    "" + html_files + 
                    "    </body>\n" +
                    "</html>\n" +
                    "\n");
        }

    }
}
