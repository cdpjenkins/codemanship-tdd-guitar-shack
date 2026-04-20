package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddItemTest {

    private final GuitarShack guitarShack = new GuitarShack();

    @Test
    void temporary_hold_is_placed_when_sufficient_stock() {
        Product product = new Product(327, "Ibanez Tube Screamer");
        guitarShack.addStockItem(new StockItem(327, 7, 0));
        Order order = new Order(guitarShack);

        order.addItem(product, 1);

        assertThat(guitarShack.getStockItem(327).getHold()).isEqualTo(1);
    }

    @Test
    void item_is_added_to_order_list_when_sufficient_stock() {
        Product product = new Product(327, "Ibanez Tube Screamer");
        guitarShack.addStockItem(new StockItem(327, 7, 0));
        Order order = new Order(guitarShack);

        order.addItem(product, 1);

        assertThat(order.getItems()).containsExactly(new OrderItem(327, 1));
    }

    @Test
    void raises_error_when_insufficient_product_stock() {
        Product product = new Product(327, "Ibanez Tube Screamer");
        guitarShack.addStockItem(new StockItem(327, 1, 0));

        Order order = new Order(guitarShack);

        assertThatThrownBy(() -> order.addItem(product, 2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.");
    }

    @Test
    void raises_error_when_insufficient_product_stock_available_due_to_a_hold() {
        Product product = new Product(327, "Ibanez Tube Screamer");
        guitarShack.addStockItem(new StockItem(327, 1, 0));
        Order order = new Order(guitarShack);

        assertThatThrownBy(() -> order.addItem(product, 2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.");
    }
}


