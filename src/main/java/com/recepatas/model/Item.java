package com.recepatas.model;

import java.math.BigDecimal;

public class Item {

    private String name;
    private ItemType type;
    private BigDecimal price;

    public Item(String name, ItemType type, BigDecimal price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
