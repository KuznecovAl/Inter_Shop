package main_pack.dao;

import main_pack.entities.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends DAO<Item>{
    List<Item> getByOrderId(long orderId) throws SQLException;
}
