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
            out.println("<!DOCTYPE HTML>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<title>Session Examples</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\t<form action=\"Login\" method=\"GET\">\n" +
                    "         Username : <input type = \"text\" name = \"username\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +
                    "         Password: <input type = \"password\" name = \"password\" />\n" +
                    "         <br>\n" +
                    "         <br>\n" +	
                    "         <input type = \"submit\" name = \"login\" />    \n" +
                    "   </form>\n" +
                    "</body>\n" +
                    "</html>");
        } else {
            RequestDispatcher rd=request.getRequestDispatcher("/Login");
            rd.forward(request, response);//method may be include or forward

            // response.sendRedirect("http://localhost:8080/session/CookieLogin");
        }

    }
}
