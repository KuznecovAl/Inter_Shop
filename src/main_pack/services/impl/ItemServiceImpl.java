package main_pack.services.impl;

import main_pack.dao.ItemDao;
import main_pack.dao.impl.ItemDaoImpl;
import main_pack.entities.Item;
import main_pack.services.ItemService;
import main_pack.services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yslabko on 07/03/2017.
 */
public class ItemServiceImpl extends AbstractService implements ItemService {
    private ItemDao itemDao = ItemDaoImpl.getInstance();

    @Override
    public Item save(Item item) {
        try {
            item = itemDao.save(item);
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + item);
        }
        return item;
    }

    @Override
    public Item get(Serializable id) {
        try {
            return itemDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error geting Item by id " + id);
        }
    }

    @Override
    public void update(Item item) {
        try {
            itemDao.update(item);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Item" + item);
        }
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public List<Item> getByOrderId(long orderId) {
        try {
            return itemDao.getByOrderId(orderId);
        } catch (SQLException e) {
            throw new ServiceException("Error getting all items");
        }
    }
}
