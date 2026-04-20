package com.codemanship;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final GuitarShack guitarShack;
    List<OrderItem> orderItems = new ArrayList<>();

    public Order(GuitarShack guitarShack) {
        this.guitarShack = guitarShack;
    }

    public void addItem(Product product, int quantity) {

        int productId = product.getId();
        StockItem stockItem = guitarShack.getStockItem(productId);

        int available = stockItem.getQuantityInStock();
        if (quantity > available) {
            throw new IllegalStateException("Insufficient stock of " + product.getDescription() + ". Only " + available + " currently available.");
        }

        orderItems.add(new OrderItem(productId, quantity));
        stockItem.addHold(quantity);
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }

    public void removeItem(Product product, int quantity) {
        int productId = product.getId();
        StockItem stockItem = guitarShack.getStockItem(productId);

        orderItems.removeIf(item -> item.productId() == productId);
        stockItem.removeHold(quantity);
    }
}
