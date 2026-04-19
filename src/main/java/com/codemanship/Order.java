package com.codemanship;

import java.util.ArrayList;
import java.util.List;

public class Order {
    List<OrderItem> orderItems = new ArrayList<>();

    public Order() {}

    public Order(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public void addItem(Product product, int quantity) {
        int available = product.getAvailable();
        if (quantity > available) {
            throw new IllegalStateException("Insufficient stock of " + product.getDescription() + ". Only " + available + " currently available.");
        }

        orderItems.add(new OrderItem(product.getId(), quantity));
        product.addHold(quantity);
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }

    public void removeItem(Product product, int quantity) {
        orderItems.removeIf(item -> item.productId() == product.getId());
        product.removeHold(quantity);
    }
}
