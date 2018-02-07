package main_pack.services.impl;

import main_pack.dao.ItemDao;
import main_pack.dao.OrderDao;
import main_pack.dao.ProductDao;
import main_pack.dao.impl.ItemDaoImpl;
import main_pack.dao.impl.OrderDaoImpl;
import main_pack.dao.impl.ProductDaoImpl;
import main_pack.entities.Item;
import main_pack.entities.Order;
import main_pack.entities.Product;
import main_pack.services.OrderService;
import main_pack.services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public class OrderServiceImpl extends AbstractService implements OrderService {
    private static volatile OrderService INSTANCE = null;

    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private ProductDao productDao = ProductDaoImpl.getInstance();
    private ItemDao itemDao = ItemDaoImpl.getInstance();

    @Override
    public Order createOrder(long userId, long productId, int quantity) {
        Order order = new Order();
        try {
            startTransaction();
            order.setId_user(userId);

            Product product = productDao.get(productId);
            if (quantity < 1) {
                quantity = 1;
            }
            order = orderDao.save(order);

            Item item = new Item(order.getId(), productId, quantity, 0);
            itemDao.save(item);
            commit();
            return order;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Order "+order);
        }
    }

    @Override
    public Order get(Serializable id) {
        try {
            return orderDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Order by id" + id);
        }
    }

    @Override
    public void update(Order order) {
        try {
            orderDao.update(order);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Order " + order);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return orderDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Order by id" + id);
        }
    }

    @Override
    public List<Order> getByUserId(long userId) {
        try {
            startTransaction();
            List<Order> orders = orderDao.getByUserId(userId);
            for (Order order : orders) {
                List<Item> items = itemDao.getByOrderId(order.getId());
                double sum = 0;
                for (Item item : items) {
                    Product product = productDao.get(item.getId_product());
                    sum += product.getPrice() * item.getQuantity();
                }
                commit();
            }
            return orders;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Orders by userId" + userId);
        }
    }

    public static OrderService getInstance() {
        OrderService itemService = INSTANCE;
        if (itemService == null) {
            synchronized (ItemDaoImpl.class) {
                itemService = INSTANCE;
                if (itemService == null) {
                    INSTANCE = itemService = new OrderServiceImpl();
                }
            }
        }

        return itemService;
    }
}
