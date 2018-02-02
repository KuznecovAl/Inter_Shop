package main_pack.services;

import java.io.Serializable;
import java.util.List;

public interface ItemService {
    Item save(Item item);

    Item get(Serializable id);

    void update(Item item);

    int delete(Serializable id);

    Item getItemByModelAndSupplier(String model, String supplier);

    List<Item> getAll();
}
