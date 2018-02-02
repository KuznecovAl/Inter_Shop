package main_pack;

import main_pack.services.ConnectionManager;
import main_pack.services.DbManagerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    static DatabaseMetaData metadata = null;

    public static void main(String[] args) {

        ResultSet rs = null;
        try (Connection connection = ConnectionManager.getConnection()) {

            Statement stmt = connection.createStatement();

            int rowsAffected = stmt.executeUpdate
                    ("update books SET GENRE='xxxx' WHERE GENRE='Фентези';");

            rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
            }



            System.out.println("Tables:");
            metadata = connection.getMetaData();
            List tables = getTablesMetadata();
            tables.forEach(System.out::println);
            getColumnsMetadata(tables);

        } catch (SQLException e) {
            System.out.println("Проблемы с получением данных");
        } catch (DbManagerException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static List getTablesMetadata() throws SQLException {
        String table[] = {"TABLE"};
        ResultSet rs = null;
        rs = metadata.getTables(null, null, null, table);
        List tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }

        return tables;
    }


    private static void getColumnsMetadata(List<String> tables) throws SQLException {
        ResultSet rs = null;
        for (String actualTable : tables) {
            rs = metadata.getColumns(null, null, actualTable, null);
            System.out.println(actualTable.toUpperCase());
            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME") + " "
                        + rs.getString("TYPE_NAME") + " "
                        + rs.getString("COLUMN_SIZE"));
            }
        }
    }

}