package main_pack.dao;

import main_pack.entities.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends DAO <Order> {
    List<Order> getByUserId(long userId) throws SQLException;
}
