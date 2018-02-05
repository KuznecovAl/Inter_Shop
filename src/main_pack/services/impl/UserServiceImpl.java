package main_pack.services.impl;

import main_pack.dao.UserDao;
import main_pack.dao.impl.UserDaoImpl;
import main_pack.entities.User;
import main_pack.services.UserService;
import main_pack.services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yslabko on 07/03/2017.
 */
public class UserServiceImpl extends AbstractService implements UserService {
    private UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public User save(User user) {
        try {
            user = userDao.save(user);
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + user);
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error geting Item by id " + id);
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Item" + user);
        }
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

//    @Override
//    public List<User> getByOrderId(long orderId) {
//        try {
//            return userDao.getByOrderId(orderId);
//        } catch (SQLException e) {
//            throw new ServiceException("Error getting all items");
//        }
//    }
}
