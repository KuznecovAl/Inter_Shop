package main_pack.services.impl;

import main_pack.entities.Product;
import main_pack.dao.ProductDao;
import main_pack.dao.impl.ProductDaoImpl;
import main_pack.services.ServiceException;
import main_pack.services.ProductService;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by yslabko on 07/03/2017.
 */
public class ProductServiceImpl extends AbstractService implements ProductService {
    private ProductDao productDao = ProductDaoImpl.getInstance();

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
