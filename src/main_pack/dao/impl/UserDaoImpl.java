package main_pack.dao.impl;

import main_pack.dao.UserDao;
import main_pack.entities.User;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao{

    private static volatile UserDao INSTANCE = null;

    private static final String saveNewUserQuery="INSERT INTO USERS (LOGIN, PASSWORD, E_MAIL) VALUES (?, ?, ?)";
    private static final String saveUserQuery = "INSERT INTO USERS (LOGIN, PASSWORD, PRIVILEGE, NAME, LNAME, E_MAIL, PHONE," +
            " ADDRESS_CITY, ADDRESS_STREET, ADDRESS_HOUSE, ADDRESS_FLAT, ADDRESS_INDEX," +
            "LANG, STATUS, BIRTHDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateUserQuery = "UPDATE USERS SET LOGIN=?, PASSWORD=?, PRIVILEGE=?, NAME=?, LNAME=?, E_MAIL=?," +
            "PHONE=?, ADDRESS_CITY=?, ADDRESS_STREET=?, ADDRESS_HOUSE=?, ADDRESS_FLAT=?, ADDRESS_INDEX=?," +
            "LANG=?, STATUS=?, BIRTHDATE=? WHERE USER_ID=?";
    private static final String getUserQuery = "SELECT * FROM USERS WHERE USER_ID=?";
    private static final String getAllUserQuery = "SELECT * FROM USERS";
    private static final String deleteUserQuery = "DELETE FROM USERS WHERE USER_ID=?";
    private static final String getUserByLoginQuery = "SELECT * FROM USERS WHERE LOGIN=?";

    private PreparedStatement psGetByLogin;
    private PreparedStatement psSave;
    private PreparedStatement psSaveNew;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;


    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    @Override
    public User getByLogin(String login) throws SQLException {
        psGetByLogin = prepareStatement(getUserByLoginQuery);
        psGetByLogin.setString(1, login);
        ResultSet rs = psGetByLogin.executeQuery();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }
    @Override
    public List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
        psGetAll = prepareStatement(getAllUserQuery);

        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong(1));
            user.setLogin(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setPrivilege(rs.getInt(4));
            user.setName(rs.getString(5));
            user.setLast_name(rs.getString(6));
            user.setEmail(rs.getString(7));
            user.setPhone(rs.getString(8));
            user.setAddress_city(rs.getString(9));
            user.setAddress_street(rs.getString(10));
            user.setAddress_building(rs.getString(11));
            user.setAddress_flat(rs.getString(12));
            user.setAddress_index(rs.getString(13));
            user.setLang(rs.getString(14));
            user.setStatus(rs.getString(15));
            list.add(user);
        }
        close(rs);
        return list;
    }
    @Override
    public User saveNew(User user) throws SQLException {
        psSaveNew = prepareStatement(saveNewUserQuery, Statement.RETURN_GENERATED_KEYS);
        psSaveNew.setString(1, user.getLogin());
        psSaveNew.setString(2, user.getPassword());
        psSaveNew.setString(3, user.getEmail());
        psSaveNew.executeUpdate();
        ResultSet rs = psSaveNew.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);
        return user;
    }
    @Override
    public User save(User user) throws SQLException {
        psSave = prepareStatement(saveUserQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, user.getLogin());
        psSave.setString(2, user.getPassword());
        psSave.setInt(3, user.getPrivilege());
        psSave.setString(4, user.getName());
        psSave.setString(5, user.getLast_name());
        psSave.setString(6, user.getEmail());
        psSave.setString(7, user.getPhone());
        psSave.setString(8, user.getAddress_city());
        psSave.setString(9, user.getAddress_street());
        psSave.setString(10, user.getAddress_building());
        psSave.setString(11, user.getAddress_flat());
        psSave.setString(12, user.getAddress_index());
        psSave.setString(13, user.getLang());
        psSave.setString(14, user.getStatus());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);
        return user;
    }
    @Override
    public User get(Serializable id) throws SQLException {
        psGet = prepareStatement(getUserQuery);

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
    public void update(User user) throws SQLException {
        psUpdate = prepareStatement(updateUserQuery);
        psUpdate.setLong(16, user.getId());
        psUpdate.setString(1, user.getLogin());
        psUpdate.setString(2, user.getPassword());
        psUpdate.setInt(3, user.getPrivilege());
        psUpdate.setString(4, user.getName());
        psUpdate.setString(5, user.getLast_name());
        psUpdate.setString(6, user.getEmail());
        psUpdate.setString(7, user.getPhone());
        psUpdate.setString(8, user.getAddress_city());
        psUpdate.setString(9, user.getAddress_street());
        psUpdate.setString(10, user.getAddress_building());
        psUpdate.setString(11, user.getAddress_flat());
        psUpdate.setString(12, user.getAddress_index());
        psUpdate.setString(13, user.getLang());
        psUpdate.setString(14, user.getStatus());
        psUpdate.setString(14, user.getBirthday().toString());
        psUpdate.executeUpdate();
    }
    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteUserQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }

    private User populateEntity(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(1));
        user.setLogin(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setPrivilege(rs.getInt(4));
        user.setName(rs.getString(5));
        user.setLast_name(rs.getString(6));
        user.setEmail(rs.getString(7));
        user.setPhone(rs.getString(8));
        user.setAddress_city(rs.getString(9));
        user.setAddress_street(rs.getString(10));
        user.setAddress_building(rs.getString(11));
        user.setAddress_flat(rs.getString(12));
        user.setAddress_index(rs.getString(13));
        user.setLang(rs.getString(14));
        user.setStatus(rs.getString(15));
        user.setBirthday(LocalDate.parse(rs.getString(16)));
        return user;
    }

}
