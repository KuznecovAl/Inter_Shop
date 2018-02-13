package main_pack;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import main_pack.dao.UserDao;
import main_pack.dao.impl.UserDaoImpl;
import main_pack.entities.User;

public class UserDaoTest {

    private UserDao userDao = UserDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException {


        int beforeSave = userDao.getAll().size();
        User newUser = userDao.save(new User("Alex", "1qaz2wsx", "Alexandr", "Kuznecov",
                "0", "xxXXxX@mail.ru", "+375297515313", "Минск", "Германовская",
                "17", "107", "002034", "ruRU", "reg", LocalDate.parse("2018-01-01")));
        int afterSave = userDao.getAll().size();
        Assert.assertNotSame(beforeSave, afterSave);
        System.out.println("x:" + beforeSave + " " + afterSave);

        newUser.setEmail("1234@mail.ru");
        userDao.update(newUser);

        User updatedUser = userDao.get(newUser.getId());
        Assert.assertEquals("Метод update не корректен", "1234@mail.ru", updatedUser.getEmail());

        userDao.delete(newUser.getId());

        afterSave = userDao.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);

    }


}
