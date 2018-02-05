package main_pack.services;

import main_pack.entities.User;

import java.io.Serializable;
import java.util.List;

/**
 * Class ItemService
 *
 * Created by yslabko on 07/02/2017.
 */
public interface UserService {

    User save(User user);

    User get(Serializable id);

    void update(User user);

    int delete(Serializable id);

    //List<User> getByOrderId(long productId);
}
