package com.codemanship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddItemTest {

    @Test
    void temporary_hold_is_placed_when_sufficient_stock() {
        Product product = new Product(327, 7, 0);
        Order order = new Order();

        order.addItem(product, 1);

        assertThat(product.getHold()).isEqualTo(1);
    }

    @Test
    void item_is_added_to_order_list_when_sufficient_stock() {
        Product product = new Product(327, 7, 0);
        Order order = new Order();

        order.addItem(product, 1);

        assertThat(order.getItems()).containsExactly(new OrderItem(327, 1));
    }
}

class Product {
    private final int id;
    private final int stock;
    private int hold;

    public Product(int id, int stock, int hold) {
        this.id = id;
        this.stock = stock;
        this.hold = hold;
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
}

class Order {

    List<OrderItem> orderItems = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        orderItems.add(new OrderItem(product.getId(), quantity));
        product.addHold(quantity);
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }
}

record OrderItem(int productId, int quantity) {
}


