package com.codemanship;

import org.junit.jupiter.api.Test;

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

    @Test
    void raises_error_when_insufficient_product_stock_available_due_to_a_hold() {
        Product product = new Product(327, "Ibanez Tube Screamer", 2, 1);
        Order order = new Order();

        assertThatThrownBy(() -> order.addItem(product, 2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.");
    }
}


