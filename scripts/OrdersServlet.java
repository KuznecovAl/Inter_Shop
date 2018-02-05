package web.servlet;

import entities.Order;
import services.OrderService;
import services.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class OrdersServlet
 *
 * Created by yslabko on 08/09/2017.
 */
@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = req.getAttribute("userId") != null ?
                (long) req.getAttribute("userId") : null;
        List<Order> orders = orderService.getByUserId(userId);

        PrintWriter pw = resp.getWriter();
        pw.print("<html>\n<head>\n<title>Cookie test</title></head>\n" +
                "  <body>Orders<br/><table><tr><th>Order Id</th><th>User Id</th><th>Sum</th></tr>");
        for (Order order : orders) {
            pw.print("<tr>\n<td>"+order.getId()+"</td><td>"+order.getUserId()+"</td><td>"+order.getTotal()+"</td>\n</tr><br/>");
        }

        pw.print("</table></body></html>");
    }
}
