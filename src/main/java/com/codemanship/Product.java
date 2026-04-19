package com.codemanship;

public class Product {
    private final int id;
    private final String description;
    private final int stock;
    private int hold;

    public Product(int id, String description, int stock, int hold) {
        this.id = id;
        this.description = description;
        this.stock = stock;
        this.hold = hold;
    }

    public int getAvailable() {
        return getStock() - getHold();
    }

    public int getHold() {
        return hold;
    }

    public void addHold(int quantity) {
        hold += quantity;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public void removeHold(int quantity) {
        hold -= quantity;
    }
}
