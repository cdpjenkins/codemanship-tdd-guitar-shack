package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ListAssert.assertThatList;

public class RemoveItemTest {

    public static final Product PRODUCT = new Product(327, "A nice guitar");
    private final GuitarShack guitarShack = new GuitarShack();

    @Test
    void temporary_hold_is_released_when_item_is_removed() {
        guitarShack.addStockItem(new StockItem(327, 1, 0));
        Order order = new Order(guitarShack);
        order.addItem(PRODUCT, 1);

        order.removeItem(PRODUCT, 1);

        assertThat(guitarShack.getStockItem(327).getHold()).isEqualTo(0);
    }

    @Test
    void item_is_removed_from_order_item_list() {
        guitarShack.addStockItem(new StockItem(327, 1, 0));
        Order order = new Order(guitarShack);
        order.addItem(PRODUCT, 1);

        order.removeItem(PRODUCT, 1);

        assertThatList(order.getItems()).isEmpty();
    }
}
