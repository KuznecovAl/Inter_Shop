package main_pack;

import main_pack.entities.User;
import main_pack.dao.UserDao;
import main_pack.dao.impl.UserDaoImpl;

import org.junit.Test;

import java.sql.SQLException;


public class RandomUserGenerator {


    private UserDao userDao = UserDaoImpl.getInstance();

    private static String s() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    @Test

    public void generateRandomUsers() throws SQLException {

        long tB = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User newUser = userDao.save(new User(s(), s(), s(), s(), (int) Math.random() * 5, s(), s(), s(), s(), s(), s(), s(), "ruRU", s()));
        }
        long tE = System.currentTimeMillis();
        System.out.println("Время заполненния базы: " + (tE - tB) + " мс");

    }

}
