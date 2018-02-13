package web.command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import web.command.Controller;
import web.command.impl.*;

@Getter
@AllArgsConstructor
public enum ControllerType {
    LOGIN("login.jsp", "Login", "login.title", new LoginController()),
    REGISTRATION("reg.jsp", "Registration", "registration.title", new RegController()),
    LOGOUT("login.jsp", "Logout", "logout.title", new LogoutController()),
    ORDERS("orders/main.jsp", "Orders", "orders.title", new OrderController()),
    ADD_PRODUCTS_AJAX("", "addProduct", "", new AddToBasketController()),
    REDUCE_PRODUCTS_AJAX("", "reduceProduct", "", new ReduceFromBasketController()),
    PRODUCTS("products/main.jsp", "Products", "products.title", new ProductController()),
    SETTINGS("settings.jsp", "Settings", "settings.title", new SettingsController());

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
// Если ничего не найдено, то на главную страницу с продуктами
        return PRODUCTS;
    }
}
