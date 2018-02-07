package main_pack.dao;

import main_pack.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends DAO<User>{

    User saveNew(User user) throws SQLException;
    List<User> getAll() throws SQLException;
    User getByLogin(String login) throws SQLException;
}
