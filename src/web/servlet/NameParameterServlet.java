package web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Class NameParameterServlet
 *
 * Created by yslabko on 08/07/2017.
 */
@WebServlet(value = "/nameservlet",
        initParams = {@WebInitParam(name="creator", value = "Yulij")})
public class NameParameterServlet extends HttpServlet {
    private ServletConfig sc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sc = config;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("errorMsg", "");

        Enumeration<String> params  = req.getParameterNames();
        PrintWriter pw = resp.getWriter();
        pw.print("<html>\n<head>\n<title>Parameter test</title></head>\n" +
                "  <body>Request parameters<br/><table><tr><th>Parameter Name</th><th>Parameter Value</th></tr>");
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            pw.print("<tr>\n<td>"+param+"</td><td>"+req.getParameter(param)+"</td>\n</tr>");
        }

        pw.print("</table>");
        pw.print("<br/><br/>Параметр сервлета: " + sc.getInitParameter("creator") + "</body></html>");
    }
}
