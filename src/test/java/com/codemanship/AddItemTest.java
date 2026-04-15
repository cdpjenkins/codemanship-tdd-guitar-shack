package com.codemanship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddItemTest {

    @Test
    void temporary_hold_is_placed_when_sufficient_stock() {
        Product product = new Product(327, "Ibanez Tube Screamer", 7, 0);
        Order order = new Order();

        order.addItem(product, 1);

        assertThat(product.getHold()).isEqualTo(1);
    }

    @Test
    void item_is_added_to_order_list_when_sufficient_stock() {
        Product product = new Product(327, "Ibanez Tube Screamer", 7, 0);
        Order order = new Order();

        order.addItem(product, 1);

        assertThat(order.getItems()).containsExactly(new OrderItem(327, 1));
    }

    @Test
    void raises_error_when_insufficient_product_stock() {
        Product product = new Product(327, "Ibanez Tube Screamer", 1, 0);
        Order order = new Order();

        assertThatThrownBy(() -> order.addItem(product, 2))
                .isInstanceOf(IllegalStateException.class)
            .hasMessage("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.");
    }
}

class Product {
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
}

class Order {
    List<OrderItem> orderItems = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (product.getStock() < quantity) {
            throw new IllegalStateException("Insufficient stock of " + product.getDescription() + ". Only " + product.getStock() + " currently available.");
        }

        orderItems.add(new OrderItem(product.getId(), quantity));
        product.addHold(quantity);
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }
}

record OrderItem(int productId, int quantity) {
}


