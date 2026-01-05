package com.bill.strategy.impl;

import org.springframework.stereotype.Component;

import com.bill.entity.Menu;
import com.bill.entity.OrderItem;
import com.bill.strategy.ItemMergeStrategy;

@Component
public class QuantityMergeStrategy implements ItemMergeStrategy {

	@Override
	public OrderItem merge(OrderItem existing, Menu menuItem, int qty) {
		if (existing == null) {
			OrderItem item = new OrderItem();
			item.setName(menuItem.getName());
			item.setPrice(menuItem.getPrice());
			item.setQuantity(qty);
			return item;
		}

		existing.setQuantity(existing.getQuantity() + qty);
		return existing;
	}

	@Override
	public OrderItem updateQuantity(OrderItem existing, Menu menuItem, int qty) {
		if (existing == null) {
			OrderItem item = new OrderItem();
			item.setName(menuItem.getName());
			item.setPrice(menuItem.getPrice());
			item.setQuantity(qty);
			return item;
		}
		
		existing.setQuantity(qty);
		return existing;
	}

}
