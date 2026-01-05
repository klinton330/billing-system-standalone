package com.bill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.entity.Bills;
import com.bill.entity.Menu;
import com.bill.entity.OrderItem;
import com.bill.services.BillingService;
import com.bill.strategy.ItemMergeStrategy;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	ItemMergeStrategy itemMergeStrategy;

	@Override
	public void addItem(Bills bills, Menu menu, int quantity) {

		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero");
		}

		OrderItem existingItem = bills.getItem(menu.getName());// Order Object or Null

		OrderItem orderItem = itemMergeStrategy.merge(existingItem, menu, quantity);
		bills.putItem(menu.getName(), orderItem);

	}

	@Override
	public double calculateTotal(Bills bill) {

		return bill.getItems().stream().mapToDouble(OrderItem::getTotalPrice).sum();
	}

	@Override
	public void updateQuantity(Bills bills, Menu menu, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero");
		}
		OrderItem existingItem = bills.getItem(menu.getName());
		OrderItem orderItem = itemMergeStrategy.updateQuantity(existingItem, menu, quantity);
		bills.putItem(menu.getName(), orderItem);
	}

}
