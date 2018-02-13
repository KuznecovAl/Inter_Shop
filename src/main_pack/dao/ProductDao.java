package main_pack.dao;

import main_pack.entities.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends DAO<Product>{

    List<Product> getAll() throws SQLException;
    Product getByModelAndSupplier(String model, String supplier) throws SQLException;
}
