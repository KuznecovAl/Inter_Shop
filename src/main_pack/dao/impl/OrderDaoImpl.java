package main_pack.dao.impl;

import main_pack.dao.OrderDao;
import main_pack.entities.Order;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class OrderDaoImpl extends AbstractDao implements OrderDao {
    private static volatile OrderDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO `ORDERS` (USER_ID, TOTAL, DATE) VALUES (?, ?, now())";
    private static final String updateQuery = "UPDATE `ORDERS` SET TOTAL=? WHERE ID=?";
    private static final String getQuery = "SELECT ID, USER_ID FROM `ORDERS` WHERE ID=?";
    private static final String getAllByUserQuery = "SELECT * FROM `ORDERS` WHERE ID_USER = ? ORDER BY ORDER_ID DESC";
    private static final String deleteQuery = "DELETE FROM `ORDERS` WHERE ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAllByUserId;
    private PreparedStatement psDelete;

    @Override
    public Order save(Order order) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, order.getId_user());
        psSave.setLong(2, order.getId_user());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            order.setId(rs.getLong(1));
        }
        close(rs);
        return order;
    }
    @Override
    public Order get(Serializable id) throws SQLException {
        psGet = prepareStatement(getQuery);
        psGet.setLong(1, (long)id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }
    @Override
    public void update(Order order) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setLong(1, order.getId());
        psUpdate.setLong(2, order.getId_user());
        psUpdate.executeUpdate();
    }
    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }
    @Override
    public List<Order> getByUserId(long userId) throws SQLException {
        psGetAllByUserId = prepareStatement(getAllByUserQuery);
        psGetAllByUserId.setLong(1, userId);
        psGetAllByUserId.execute();
        ResultSet rs = psGetAllByUserId.getResultSet();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);

        return list;
    }
    private Order populateEntity(ResultSet rs) throws SQLException {
        Order entity = new Order();
            entity.setId(rs.getLong(1));
            entity.setId_user(rs.getLong(2));
            entity.setDate_time(LocalDateTime.parse(rs.getString(3),DateTimeFormatter.ofPattern("y-M-d H:m:s.S")));
            entity.setStatus(rs.getString(4));
        return entity;
    }
    public static OrderDao getInstance() {
        OrderDao orderDao = INSTANCE;
        if (orderDao == null) {
            synchronized (OrderDaoImpl.class) {
                orderDao = INSTANCE;
                if (orderDao == null) {
                    INSTANCE = orderDao = new OrderDaoImpl();
                }
            }
        }

        return orderDao;
    }
}
