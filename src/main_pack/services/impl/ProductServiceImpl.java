package main_pack.services.impl;

import main_pack.entities.Product;
import main_pack.dao.ProductDao;
import main_pack.dao.impl.ProductDaoImpl;
import main_pack.services.ServiceException;
import main_pack.services.ProductService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public class ProductServiceImpl extends AbstractService implements ProductService {

    private static volatile ProductService INSTANCE = null;
    private ProductDao productDao = ProductDaoImpl.getInstance();

    public static ProductService getInstance() {
        ProductService productService = INSTANCE;
        if (productService == null) {
            synchronized (ProductServiceImpl.class) {
                productService = INSTANCE;
                if (productService == null) {
                    INSTANCE = productService = new ProductServiceImpl();
                }
            }
        }

        return productService;
    }

    @Override
    public Product getByModelAndSupplier(String model, String supplier) {
        try {
            return productDao.getByModelAndSupplier(model, supplier);
        } catch (SQLException e) {
            throw new ServiceException("Error getting by Supplier:" + supplier + " and Model:" + model);
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            startTransaction();
            List<Product> list = productDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Products");
        }
    }

    @Override
    public Product save(Product product) {
        try {
            product = productDao.save(product);
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + product);
        }
        return product;
    }

    @Override
    public Product get(Serializable id) {
        try {
            return productDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error geting Item by id " + id);
        }
    }

    @Override
    public void update(Product product) {
        try {
            productDao.update(product);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Item" + product);
        }
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }


}
