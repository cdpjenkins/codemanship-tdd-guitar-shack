package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ListAssert.assertThatList;

public class RemoveItemTest {

    @Test
    void temporary_hold_is_released_when_item_is_removed() {
        Product product = new Product(327, "A nice guitar", 1, 1);
        Order order = new Order();

        order.removeItem(product, 1);

        assertThat(product.getHold()).isEqualTo(0);
    }

    @Test
    void item_is_removed_from_order_item_list() {
        Product product = new Product(327, "A nice guitar", 1, 1);
        Order order = new Order();

        order.removeItem(product, 1);

        assertThatList(order.getItems()).isEmpty();
    }
}
