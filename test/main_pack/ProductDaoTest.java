package main_pack;

import main_pack.dao.ProductDao;
import main_pack.dao.impl.ProductDaoImpl;
import main_pack.entities.Product;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class ProductDaoTest {

    private ProductDao productDao = ProductDaoImpl.getInstance();


    @Test
    public void fullTest() throws SQLException {


        int beforeSave = productDao.getAll().size();
        Product newProduct = productDao.save(new Product("Отвертки", "Отвертка", "Stanley", "Multibit",
                "catalog", "5x50s 5x30d", "ОТВЕРТКА STUBBY MULTIBIT СО СМЕННЫМИ ВСТАВКАМИ, STANLEY", 10, 15.05F));
        Product newProduct2 = productDao.save(new Product("Отвертки", "Отвертка", "Stanley", "Multibit2",
                "catalog2", "5x50s 5x31d", "ОТВЕРТКА STUBBY MULTIBITXTRA СО СМЕННЫМИ ВСТАВКАМИ, STANLEY", 15, 25.05F));
        Product newProduct3 = productDao.save(new Product("Отвертки", "Отвертка", "ХРОМОВАНАДИЙ", "Крестовая",
                "catalog3", "3.4.5.6.7.8", "ОТВЕРТКА КРЕСТОВАЯ ДЕШЕВАЯ", 1034, 2.15F));
        Product newProduct4 = productDao.save(new Product("Молотки", "Молоток", "молоточный завод №18", "Кувалда для ноутбука",
                "catalog4", "15кг", "Кувалда для уничтожения секретной информации", 10, 11.5F));


        int afterSave = productDao.getAll().size();
        Assert.assertNotSame(beforeSave, afterSave);
        System.out.println("x:" + beforeSave + " " + afterSave);

        newProduct.setPrice(23.45F);
        productDao.update(newProduct);

        Product updatedProduct = productDao.get(newProduct.getId());
        //Assert.assertEquals( 23.45F, updatedProduct.getPrice(),0.0F);

        //productDao.delete(newProduct.getId());

        afterSave = productDao.getAll().size();
        Assert.assertEquals(beforeSave, afterSave);

    }


}
