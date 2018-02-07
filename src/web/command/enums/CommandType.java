package web.command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import web.command.Controller;
import web.command.impl.LoginController;
import web.command.impl.LogoutController;
import web.command.impl.OrderController;
import web.command.impl.ProductController;

@Getter
@AllArgsConstructor
public enum CommandType {
    LOGIN("login.jsp", "Login", new LoginController()),
    LOGOUT("login.jsp", "Logout", new LogoutController()),
    ORDERS("orders/main.jsp", "Orders", new OrderController()),
    PRODUCTS("products/main.jsp", "Products", new ProductController());

    private String pagePath;
    private String pageName;
    private Controller controller;

    public static CommandType getByPageName(String page) {
        for (CommandType type : CommandType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
// Если ничего не найдено, то на главную страницу с продуктами
        return PRODUCTS;
    }
}
