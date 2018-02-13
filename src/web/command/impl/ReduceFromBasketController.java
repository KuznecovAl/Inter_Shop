package web.command.impl;

import com.google.gson.Gson;
import main_pack.services.OrderService;
import main_pack.services.impl.OrderServiceImpl;
import web.command.Controller;
import web.vo.BasketVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ReduceFromBasketController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasketVO basket = (BasketVO) req.getSession().getAttribute("basket");
        if (basket == null) {
            basket = new BasketVO(new HashMap<>());
            req.getSession().setAttribute("basket", basket);
        }
        long productId = Long.parseLong(req.getParameter("productId"));
//        String id = req.getReader().readLine();
//        Gson in = new Gson();
//        long productId = in.fromJson(id, Long.class);
        AtomicInteger count = basket.getBasket().get(productId);
        int currentCount = 0;
        if (count == null) {
            count = new AtomicInteger();
            count.set(0);
            currentCount = 0;
        } else if(count.get()>0){
            currentCount = count.decrementAndGet();
        }else{
            currentCount=0;
        }
        basket.getBasket().put(productId, count);
        PrintWriter writer = resp.getWriter();
        writer.print(new Gson().toJson(currentCount));
    }
}
