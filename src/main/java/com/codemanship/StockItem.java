package com.codemanship;

public class StockItem {
    private final int productId;
    private int quantityInStock;
    private int hold;

    public StockItem(int productId, int quantityInStock, int hold) {
        this.productId = productId;
        this.quantityInStock = quantityInStock;
        this.hold = hold;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void addHold(int quantity) {
        hold += quantity;
    }

    public void removeHold(int quantity) {
        hold -= quantity;
    }

    public int getHold() {
        return hold;
    }
}
