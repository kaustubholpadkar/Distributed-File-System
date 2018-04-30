import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        String username, password;

        // out.println("You are logged in");
        if (session == null) {
            session = request.getSession(true);

            username = request.getParameter("username");
            password = request.getParameter("password");

            session.setAttribute("username", username);
            session.setAttribute("password", password);

            out.println("<h1>" + username + "Now Logged In" + "</h1>");

        } else {
            username = (String) session.getAttribute("username");
            password = (String) session.getAttribute("password");

            out.println("<h1>" + username + "Already Logged In" + "</h1>");
            
        }
        
        RequestDispatcher rd=request.getRequestDispatcher("/DFS");
        rd.forward(request, response);//method may be include or forward
        
        


    }
}
