package main_pack.utils;

import java.sql.Connection;


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
