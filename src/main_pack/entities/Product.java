package main_pack.entities;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Product {

    private long id;
    private String category;
    private String name;
    private String supplier;
    private String model;
    private String pics;
    private String attributes;
    private String description;
    private int quantity;
    private float price;


    public Product(String category, String name, String supplier,
                   String model, String pics, String attributes, String description,
                   int quantity, float price) {
        this.id = -1;
        this.category = category;
        this.name = name;
        this.supplier = supplier;
        this.model = model;
        this.pics = pics;
        this.attributes = attributes;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
}
