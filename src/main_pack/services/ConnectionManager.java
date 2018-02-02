package main_pack.services;

import java.sql.Connection;

/**
 * Class ConnectionManager
 *
 * Created by yslabko on 07/01/2017.
 */
public class ConnectionManager {
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    public static Connection getConnection() throws DbManagerException {
        try {
            if (tl.get() == null) {
                tl.set(DataSource.getInstance().getConnection());
            }
            return tl.get();
        } catch (Exception e) {
            throw new DbManagerException("Ошибка получения соединения " +  e.getMessage());
        }
    }
}
