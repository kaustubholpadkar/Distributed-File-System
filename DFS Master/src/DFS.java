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
        

        String upload_file = "";
        
        upload_file += "<h3>Upload File</h3>";
        upload_file += "<form method = \"POST\" action = \"UploadFile\" name=\"form2\" enctype = \"multipart/form-data\">\n";
        upload_file += "<div class=\"form-group\">";
        upload_file += "<label for=\"exampleFormControlFile1\">Select File to Upload</label>";
        upload_file += "<input type=\"file\" class=\"form-control-file\" name=\"file\" id=\"exampleFormControlFile1\">";
        upload_file += "</div>";
        upload_file += "<input type=\"submit\" class=\"btn btn-primary\">Upload</input>";
        upload_file += "</form>";
        
        String html_files = "";
        
        html_files += "	<div class=\"row\">";
        html_files += "<div class=\"col-md-6\">" + upload_file + "</div>";
        html_files += "<div class=\"col-md-6\">";
        html_files += "<h3>Your Files</h3>";
        html_files += "<ul class=\"list-group\">";
        
        for (int i = 0; i < files.size(); i++) {
        	html_files += "<li class=\"list-group-item list-group-item-success\">";
        	html_files += "<h4 class=\"list-group-item-heading\">" + files.get(i)+ "</h4>";
        	html_files += "<a class=\"list-group-item-text\" href = 'DownloadFile?filename1=" + files.get(i) + "'> Download </a>";
        	html_files += "<a class=\"list-group-item-text\" href = 'DeleteFile?filename4=" + files.get(i) + "'> Delete </a>";
        	html_files += "</li>";
        }
        html_files += "</ul>";
        html_files += "</div\">";
        html_files += "</div>";
        
        // String password = (String) session.getAttribute("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session == null) {
            RequestDispatcher rd=request.getRequestDispatcher("/");
            rd.forward(request, response);//method may be include or forward
        } else {

            String navigation = "<nav class=\"navbar navbar-light bg-light\"><div class=\"container-fluid\">";
            navigation += "<div class=\"navbar-header\"><a class=\"navbar-brand\" href=\"#\">Distributed File System</a></div>";
            navigation += "<ul class=\"nav navbar-nav navbar-right\">";
            navigation += "<li><a href=\"#\">Welcome "+ username + " !</a></li>";
            navigation += "<li><a href=\"Logout\"><span class=\"glyphicon glyphicon-log-out\"></span> Logout</a></li>";
            navigation += "</ul></div></nav>";
            
            
            
			out.println("<!DOCTYPE html>\n" +
                    "<html lang='en'>\n" +
                    "    \n" +
                    "    <head>\n" +
                    "        <meta charset=\"utf-8\">\n" +
                    "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "		 <meta name='viewport' content='width=device-width, initial-scale=1'>" +
                    "		 <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>" + 
                    "		 <link rel='stylesheet' href='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'>" +
                    "		 <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" +
                    
                    "        <title>Index Servlet</title>\n" +
                    "    </head>\n" +
                    "    <body>\n" +

                    "<div class=\"container\">" +
                    "        \n" +
                    navigation  +                    
                    
                    
                    
                    
                    "        <!--h4>\n" + 
                    "Welcome " + username + " !" + 
                    "		</h4-->\n" +
                    "        \n" +
                    "        <hr>\n" +
                    "        <!--h2>Download Setup</h2>\n" +
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
                    "        </form>\n" +
                    "        <hr>\n" +
                    "        \n" +
                    "        <h2>Delete File</h2>\n" +
                    "	<div class=\"row\">"+
                    "<div class=\"col-md-6\"></div>" + 
                    "<div class=\"col-md-6\">" + 
                    "        <form method = \"GET\" action = \"DeleteFile\" name=\"form4\">\n" +

					"		<div class='form-group'>" + 
                    "            <label>Filename:</label> <input type=\"text\" id=\"filename4\" name=\"filename4\" class=\"form-control\">\n" +
                    "		</div>" +
                    "		<button type=\"submit\" class=\"btn btn-default\">Delete File</button>" +
                    
                    "        </form>\n" +
                    "</div>" +
                    // "<div class=\"col-sm-4\"></div>" + 
                    " </div-->" +
                    "" + html_files + 
                    "</div>" + 
                    "    </body>\n" +
                    "</html>\n" +
                    "\n");
        }

    }
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		
		
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        
        ArrayList<String> files = node.getFiles(username);
        
        files.add("File 1");
        files.add("File 2");
        files.add("File 3");
        files.add("File 4");

        String upload_file = "";
        
        upload_file += "<h3>Upload File</h3>";
        upload_file += "<form method = \"POST\" action = \"UploadFile\" name=\"form2\" enctype = \"multipart/form-data\">\n";
        upload_file += "<div class=\"form-group\">";
        upload_file += "<label for=\"exampleFormControlFile1\">Select File to Upload</label>";
        upload_file += "<input type=\"file\" class=\"form-control-file\" name=\"file\" id=\"exampleFormControlFile1\">";
        upload_file += "</div>";
        upload_file += "<input type=\"submit\" class=\"btn btn-primary\">Upload</input>";
        upload_file += "</form>";
        
        String html_files = "";
        
        html_files += "	<div class=\"row\">";
        html_files += "<div class=\"col-md-6\">" + upload_file + "</div>";
        html_files += "<div class=\"col-md-6\">";
        html_files += "<h3>Your Files</h3>";
        html_files += "<ul class=\"list-group\">";
        
        for (int i = 0; i < files.size(); i++) {
        	html_files += "<li class=\"list-group-item list-group-item-success\">";
        	html_files += "<h4 class=\"list-group-item-heading\">" + files.get(i)+ "</h4>";
        	html_files += "<a class=\"list-group-item-text\" href = 'DownloadFile?filename1=" + files.get(i) + "'> Download </a>";
        	html_files += "<a class=\"list-group-item-text\" href = 'DeleteFile?filename4=" + files.get(i) + "'> Delete </a>";
        	html_files += "</li>";
        }
        html_files += "</ul>";
        html_files += "</div\">";
        html_files += "</div>";
        
        // String password = (String) session.getAttribute("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session == null) {
            RequestDispatcher rd=request.getRequestDispatcher("/");
            rd.forward(request, response);//method may be include or forward
        } else {

            String navigation = "<nav class=\"navbar navbar-light bg-light\"><div class=\"container-fluid\">";
            navigation += "<div class=\"navbar-header\"><a class=\"navbar-brand\" href=\"#\">Distributed File System</a></div>";
            navigation += "<ul class=\"nav navbar-nav navbar-right\">";
            navigation += "<li><a href=\"#\">Welcome "+ username + " !</a></li>";
            navigation += "<li><a href=\"Logout\"><span class=\"glyphicon glyphicon-log-out\"></span> Logout</a></li>";
            navigation += "</ul></div></nav>";
            
            
            
			out.println("<!DOCTYPE html>\n" +
                    "<html lang='en'>\n" +
                    "    \n" +
                    "    <head>\n" +
                    "        <meta charset=\"utf-8\">\n" +
                    "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "		 <meta name='viewport' content='width=device-width, initial-scale=1'>" +
                    "		 <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>" + 
                    "		 <link rel='stylesheet' href='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'>" +
                    "		 <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" +
                    
                    "        <title>Index Servlet</title>\n" +
                    "    </head>\n" +
                    "    <body>\n" +

                    "<div class=\"container\">" +
                    "        \n" +
                    navigation  +                    
                    
                    
                    
                    
                    "        <!--h4>\n" + 
                    "Welcome " + username + " !" + 
                    "		</h4-->\n" +
                    "        \n" +
                    "        <hr>\n" +
                    "        <!--h2>Download Setup</h2>\n" +
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
                    "        </form>\n" +
                    "        <hr>\n" +
                    "        \n" +
                    "        <h2>Delete File</h2>\n" +
                    "	<div class=\"row\">"+
                    "<div class=\"col-md-6\"></div>" + 
                    "<div class=\"col-md-6\">" + 
                    "        <form method = \"GET\" action = \"DeleteFile\" name=\"form4\">\n" +

					"		<div class='form-group'>" + 
                    "            <label>Filename:</label> <input type=\"text\" id=\"filename4\" name=\"filename4\" class=\"form-control\">\n" +
                    "		</div>" +
                    "		<button type=\"submit\" class=\"btn btn-default\">Delete File</button>" +
                    
                    "        </form>\n" +
                    "</div>" +
                    // "<div class=\"col-sm-4\"></div>" + 
                    " </div-->" +
                    "" + html_files + 
                    "</div>" + 
                    "    </body>\n" +
                    "</html>\n" +
                    "\n");
        }

    }
}
