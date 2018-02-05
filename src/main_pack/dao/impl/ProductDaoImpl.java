package main_pack.dao.impl;

import main_pack.dao.ProductDao;
import main_pack.entities.Product;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends AbstractDao implements ProductDao {

    private static volatile ProductDao INSTANCE = null;

    private static final String saveProductQuery = "INSERT INTO PRODUCTS (CATEGORY, NAME, SUPPLIER, MODEL, " +
            "PICS, ATTRIBUTES, DESCRIPTION, QUANTITY, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateProductQuery = "UPDATE PRODUCTS SET CATEGORY=?, NAME=?, SUPPLIER=?, MODEL=?," +
            "PICS=?, ATTRIBUTES=?, DESCRIPTION=?, QUANTITY=?, PRICE=? WHERE PRODUCT_ID=?";
    private static final String getProductQuery = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID=?";
    private static final String getAllProductsQuery = "SELECT * FROM PRODUCTS";
    private static final String deleteProductQuery = "DELETE FROM PRODUCTS WHERE PRODUCT_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;


    private ProductDaoImpl() {
    }

    public static ProductDao getInstance() {
        ProductDao productDao = INSTANCE;
        if (productDao == null) {
            synchronized (ProductDaoImpl.class) {
                productDao = INSTANCE;
                if (productDao == null) {
                    INSTANCE = productDao = new ProductDaoImpl();
                }
            }
        }
        return productDao;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        psGetAll = prepareStatement(getAllProductsQuery);

        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong(1));
            product.setCategory(rs.getString(2));
            product.setName(rs.getString(3));
            product.setSupplier(rs.getString(4));
            product.setModel(rs.getString(5));
            product.setPics(rs.getString(6));
            product.setAttributes(rs.getString(7));
            product.setDescription(rs.getString(8));
            product.setQuantity(rs.getInt(9));
            product.setPrice(rs.getFloat(10));
            list.add(product);
        }
        close(rs);
        return list;
    }

    @Override
    public Product save(Product product) throws SQLException {
        psSave = prepareStatement(saveProductQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, product.getCategory());
        psSave.setString(2, product.getName());
        psSave.setString(3, product.getSupplier());
        psSave.setString(4, product.getModel());
        psSave.setString(5, product.getPics());
        psSave.setString(6, product.getAttributes());
        psSave.setString(7, product.getDescription());
        psSave.setInt(8, product.getQuantity());
        psSave.setFloat(9, product.getPrice());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            product.setId(rs.getLong(1));
        }
        close(rs);
        return product;
    }

    @Override
    public Product get(Serializable id) throws SQLException {
        psGet = prepareStatement(getProductQuery);

        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong(1));
            product.setCategory(rs.getString(2));
            product.setName(rs.getString(3));
            product.setSupplier(rs.getString(4));
            product.setModel(rs.getString(5));
            product.setPics(rs.getString(6));
            product.setAttributes(rs.getString(7));
            product.setDescription(rs.getString(8));
            product.setQuantity(rs.getInt(9));
            product.setPrice(rs.getInt(10));
            return product;
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Product product) throws SQLException {
        psUpdate = prepareStatement(updateProductQuery);
        psUpdate.setLong(10, product.getId());
        psUpdate.setString(1, product.getCategory());
        psUpdate.setString(2, product.getName());
        psUpdate.setString(3, product.getSupplier());
        psUpdate.setString(4, product.getModel());
        psUpdate.setString(5, product.getPics());
        psUpdate.setString(6, product.getAttributes());
        psUpdate.setString(7, product.getDescription());
        psUpdate.setInt(8, product.getQuantity());
        psUpdate.setFloat(9, product.getPrice());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteProductQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }


}
