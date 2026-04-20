package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddItemTest {

    public static final Product PRODUCT1 = new Product(327, "Ibanez Tube Screamer");
    public static final int PRODUCT1_ID = 327;

    private final GuitarShack guitarShack = new GuitarShack();

    @Test
    void temporary_hold_is_placed_when_sufficient_stock() {
        guitarShack.addStockItem(new StockItem(PRODUCT1_ID, 7, 0));
        Order order = new Order(guitarShack);

        order.addItem(PRODUCT1, 1);

        assertThat(guitarShack.getStockItem(PRODUCT1_ID).getHold()).isEqualTo(1);
    }

    @Test
    void item_is_added_to_order_list_when_sufficient_stock() {
        guitarShack.addStockItem(new StockItem(PRODUCT1_ID, 7, 0));
        Order order = new Order(guitarShack);

        order.addItem(PRODUCT1, 1);

        assertThat(order.getItems()).containsExactly(new OrderItem(PRODUCT1_ID, 1));
    }

    @Test
    void raises_error_when_insufficient_product_stock() {
        guitarShack.addStockItem(new StockItem(PRODUCT1_ID, 1, 0));

        Order order = new Order(guitarShack);

        assertThatThrownBy(() -> order.addItem(PRODUCT1, 2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.");
    }

    @Test
    void raises_error_when_insufficient_product_stock_available_due_to_a_hold() {
        guitarShack.addStockItem(new StockItem(PRODUCT1_ID, 1, 0));
        Order order = new Order(guitarShack);

        assertThatThrownBy(() -> order.addItem(PRODUCT1, 2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.");
    }
}


