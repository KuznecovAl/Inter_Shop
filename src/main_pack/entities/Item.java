package main_pack.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class Item {

    private long id;
    private long id_order;
    private long id_product;
    private long quantity;
    private long discont;


    public Item(long id_order, long id_product, long quantity, long discont) {
        this.id = -1;
        this.id_order = id_order;
        this.id_product = id_product;
        this.quantity = quantity;
        this.discont = discont;
    }
}

