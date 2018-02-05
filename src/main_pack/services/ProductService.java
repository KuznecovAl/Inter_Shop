package main_pack.services;

import main_pack.entities.Product;

import java.io.Serializable;

/**
 * Class ItemService
 *
 * Created by yslabko on 07/02/2017.
 */
public interface ProductService {

    Product save(Product product);

    Product get(Serializable id);

    void update(Product product);

    int delete(Serializable id);


}
