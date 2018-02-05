package web.servlet;

import org.apache.commons.lang3.math.NumberUtils;
import services.OrderService;
import services.impl.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class CreateOrderForwardServlet
 *
 * Created by yslabko on 08/09/2017.
 */
@WebServlet("/forward")
public class CreateOrderForwardServlet extends HttpServlet {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = NumberUtils.toLong(req.getParameter("userId"));
        long productId = NumberUtils.toLong(req.getParameter("productId"));
        int quantity = NumberUtils.toInt(req.getParameter("quantity"));
        orderService.createOrder(userId, productId, quantity);
        req.setAttribute("userId", userId);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/orders");
        dispatcher.forward(req, resp);
    }
}
