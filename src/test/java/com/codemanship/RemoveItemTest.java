package com.codemanship;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RemoveItemTest {

    @Test
    void temporary_hold_is_released_when_item_is_removed() {
        Product product = new Product(327, "A nice guitar", 1, 1);
        Order order = new Order();

        order.removeItem(product, 1);

        assertThat(product.getHold()).isEqualTo(0);
    }
}
