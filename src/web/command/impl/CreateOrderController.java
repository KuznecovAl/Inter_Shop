package web.command.impl;

import main_pack.entities.Order;
import main_pack.entities.User;
import main_pack.services.OrderService;
import main_pack.services.impl.OrderServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateOrderController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        long productId = (long)req.getAttribute("productId");
        Order order = orderService.createOrder(user.getId(), productId, 0);

        req.setAttribute("order", order);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}