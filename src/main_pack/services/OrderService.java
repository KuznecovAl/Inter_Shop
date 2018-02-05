package main_pack.services;

import main_pack.entities.Order;

import java.io.Serializable;
import java.util.List;


public interface OrderService {

    Order createOrder(long userId, long productId, int quantity);
    Order get(Serializable id);
    void update(Order order);
    int delete(Serializable id);
    List<Order> getByUserId(long userId);
}
