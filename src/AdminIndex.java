import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminIndex extends HttpServlet {
	NameNode node;

	   public void init( ){
	      // Get the file location where it would be stored.
	      //filePath = getServletContext().getInitParameter("file-upload"); 
	      node = NameNode.getNode();
	   }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // out.println("You are logged in");
        

        if (true) {
        	
        	String navigation = "<nav class=\"navbar navbar-light bg-light\"><div class=\"container-fluid\">";
            navigation += "<div class=\"navbar-header\"><a class=\"navbar-brand\" href=\"#\">Distributed File System</a></div>";
            navigation += "<ul class=\"nav navbar-nav navbar-right\">";
            navigation += "<li><a href=\"DownloadDataNodeSetup\"><span class=\"glyphicon glyphicon-download\"></span> Download Setup</a></li>";
            navigation += "</ul></div></nav>";
            
            out.println("<!DOCTYPE HTML>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "		 <meta name='viewport' content='width=device-width, initial-scale=1'>" +
                    "		 <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>" + 
                    "		 <link rel='stylesheet' href='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'>" +
                    "		 <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" +
                    
                    "<title>Distributed File System</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">" +
                    navigation +
                    "<hr>"+
                    "<div class=\"row\">" +
                    "<div class=\"col-md-4\">" +
                    "<h3>Configuration</h3>"+
                    "<h4>Replication Factor: "+  node.getReplicationFactor() + "</h4>"+
                    "<h4>Block Size: "+  node.getBlockSize() + "</h4>"+
                    "<hr>" +
                    "<h3>Update Configuration</h3>" +
                    "\t<form action=\"Admin\" method=\"GET\">\n" +
                    "		<div class=\"form-group\">" +
                    "         <label for=\"replication_factor\">Replication Factor : </label>"+
                    "		  <input type = \"text\" class=\"form-control\" name = \"replication_factor\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +
                    "         <label for=\"blocksize\">Blocksize : </label>"+
                    "         <input type = \"text\" class=\"form-control\" name = \"blocksize\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +	
                    "			<input type=\"submit\" class=\"btn btn-default\" value=\"Update\"></input>" + 
                    "   </form>\n" +
                    "</div>" + 
                    "<div class=\"col-md-8\">" +                    
                    "</div>" + 
                    "</div>"+
                    "</div>"+
                    "</body>\n" +
                    "</html>");
        } 

    }
}
