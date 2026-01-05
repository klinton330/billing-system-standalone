package com.bill.strategy;

import com.bill.entity.Menu;
import com.bill.entity.OrderItem;

public interface ItemMergeStrategy {
    OrderItem merge(OrderItem existing, Menu incoming, int qty);
    OrderItem updateQuantity(OrderItem existing, Menu incoming, int qty);
}