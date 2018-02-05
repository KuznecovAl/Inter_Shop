package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class CookieServlet
 *
 * Created by yslabko on 08/07/2017.
 */
@WebServlet("/cookie")
public class CookieServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (req.getCookies().length > 1) {
            Cookie cookie = new Cookie("SweetCookie", "Chocolate");
            cookie.setMaxAge(60);
            resp.addCookie(cookie);
        }

        PrintWriter pw = resp.getWriter();
        pw.print("<html>\n<head>\n<title>Cookie test</title></head>\n" +
                "  <body>Cookie<br/><table><tr><th>Cookie Name</th><th>Cookie Value</th></tr>");
        for (Cookie cookie : req.getCookies()) {
            pw.print("<tr>\n<td>"+cookie.getName()+"</td><td>"+cookie.getValue()+"</td>\n</tr><br/>");
        }

        pw.print("</table></body></html>");

    }
}
