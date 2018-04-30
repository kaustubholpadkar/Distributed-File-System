import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class DFS extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
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
                    "    </body>\n" +
                    "</html>\n" +
                    "\n");
        }

    }
}
