package com.codemanship;

import java.util.HashMap;
import java.util.Map;

public class GuitarShack {

    private final Map<Integer, StockItem> stockItems = new HashMap<>();

    public StockItem getStockItem(int productId) {
        return stockItems.get(productId);
    }

    public void addStockItem(StockItem stockItem) {
        stockItems.put(stockItem.getProductId(), stockItem);
    }
}
