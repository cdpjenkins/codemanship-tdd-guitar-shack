package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ListAssert.assertThatList;

public class RemoveItemTest {

    public static final int PRODUCT_ID = 327;
    public static final Product PRODUCT = new Product(PRODUCT_ID, "A nice guitar");
    private final GuitarShack guitarShack = new GuitarShack();
    private final Order order = new Order(guitarShack);

    @Test
    void temporary_hold_is_released_when_item_is_removed() {
        givenStockItem(new StockItem(PRODUCT_ID, 1, 0));
        givenAnOrderWith(PRODUCT, 1);

        order.removeItem(PRODUCT, 1);

        assertThat(guitarShack.getStockItem(PRODUCT_ID).getHold()).isEqualTo(0);
    }

    private void givenAnOrderWith(Product product, int quantity) {
        order.addItem(product, quantity);
    }

    @Test
    void item_is_removed_from_order_item_list() {
        givenStockItem(new StockItem(PRODUCT_ID, 1, 0));
        givenAnOrderWith(PRODUCT, 1);

        order.removeItem(PRODUCT, 1);

        assertThatList(order.getItems()).isEmpty();
    }

    private void givenStockItem(StockItem stockItem) {
        guitarShack.addStockItem(stockItem);
    }
}
