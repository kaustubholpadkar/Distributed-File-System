import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Index extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // out.println("You are logged in");
        HttpSession session = request.getSession(false);

        if (session == null) {
        	
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
                    "<h3>Login</h3>" +
                    "\t<form action=\"Login\" method=\"GET\">\n" +
                    "		<div class=\"form-group\">" +
                    "         <label for=\"username\">Username : </label>"+
                    "		  <input type = \"text\" class=\"form-control\" name = \"username\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +
                    "         <label for=\"password\">Password : </label>"+
                    "         <input type = \"password\" class=\"form-control\" name = \"password\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +	
                    "			<input type=\"submit\" class=\"btn btn-default\" value=\"Login\"></input>" + 
                    "   </form>\n" +
                    "</div>" + 
                    "<div class=\"col-md-8\">" +                    
                    "</div>" + 
                    "</div>"+
                    "</div>"+
                    "</body>\n" +
                    "</html>");
        } else {
            RequestDispatcher rd=request.getRequestDispatcher("/Login");
            rd.forward(request, response);//method may be include or forward

            // response.sendRedirect("http://localhost:8080/session/CookieLogin");
        }

    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // out.println("You are logged in");
        HttpSession session = request.getSession(false);

        if (session == null) {
        	
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
                    "<h3>Login</h3>" +
                    "\t<form action=\"Login\" method=\"GET\">\n" +
                    "		<div class=\"form-group\">" +
                    "         <label for=\"username\">Username : </label>"+
                    "		  <input type = \"text\" class=\"form-control\" name = \"username\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +
                    "         <label for=\"password\">Password : </label>"+
                    "         <input type = \"password\" class=\"form-control\" name = \"password\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +	
                    "			<input type=\"submit\" class=\"btn btn-default\">Login</input>" + 
                    "   </form>\n" +
                    "</div>" + 
                    "<div class=\"col-md-8\">" +                    
                    "</div>" + 
                    "</div>"+
                    "</div>"+
                    "</body>\n" +
                    "</html>");
        } else {
            RequestDispatcher rd=request.getRequestDispatcher("/Login");
            rd.forward(request, response);//method may be include or forward

            // response.sendRedirect("http://localhost:8080/session/CookieLogin");
        }
    	
    }
}
