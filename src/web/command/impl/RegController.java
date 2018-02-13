package web.command.impl;

import main_pack.entities.User;
import main_pack.services.UserService;
import main_pack.services.impl.UserServiceImpl;
import web.auth.Encoder;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegController implements Controller {

    UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("e_mail");

        if (login == null || password == null || email == null
                || login.contains(" ") || password.contains(" ") || email.contains(" ") ||
                login.equals("") || password.equals("") || email.equals("")) {
            req.setAttribute("errorMsg", "You input wrong data");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "Reg form");
            dispatcher.forward(req, resp);
            return;
        }
        if (userService.getByLogin(login) == null) {
            User user = new User();
            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(Encoder.encode(password));
            user.setPrivilege("3");
            user.setStatus("new");
            userService.saveNew(user);
            req.getSession().setAttribute("user", user);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=orders");
            return;
        } else {
            req.setAttribute("errorMsg", "login is busy");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "Reg form");
            dispatcher.forward(req, resp);


            return;
        }


    }
}
