package main_pack.services;

import main_pack.entities.Product;

import java.io.Serializable;
import java.util.List;


public interface ProductService {

    Product save(Product product);

    Product get(Serializable id);

    void update(Product product);

    int delete(Serializable id);
    List<Product> getAll();


}
