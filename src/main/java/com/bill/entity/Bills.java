package com.bill.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Bills {
	private final Map<String, OrderItem> items = new HashMap<>();

	public Map<String, OrderItem> putItem(String name, OrderItem item) {
		items.put(name, item);
		return items;
	}

	public OrderItem getItem(String name) {
		return items.get(name);
	}

	public void removeItem(String name) {
		items.remove(name);
	}

	public Collection<OrderItem> getItems() {
		return items.values();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}
}
