import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class Index extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE HTML>\n" +
                "<html lang=\"en\">\n" +
                "   <head>\n" +
                "\t   <meta charset=\"UTF-8\">\n" +
                "      <title>File Uploading Form</title>\n" +
                "   </head>\n" +
                "   <body>\n" +
                "      <h3>File Upload:</h3>\n" +
                "      <p>Select a file to upload: <br />\n" +
                "      <form action = \"UploadServlet\" method = \"post\" enctype = \"multipart/form-data\">\n" +
                "         <input type = \"file\" name = \"file\" size = \"50\" />\n" +
                "         <br /><br />\n" +
                "         <input type = \"submit\" value = \"Upload File\" />\n" +
                "      </form>\n" +
                " <a href=\"DownloadServlet\">download the jsp file</a>\n" +
                "   </body>\n" +
                "</html>");
    }

}
