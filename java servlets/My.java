import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class My extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter p = res.getWriter();
        p.println("Hello servlet");
    }
}
