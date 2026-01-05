package com.bill.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.entity.Bills;
import com.bill.entity.BillsEntity;
import com.bill.entity.Menu;
import com.bill.entity.OrderItem;
import com.bill.entity.OrderItemEntity;
import com.bill.repo.BillsRepositoy;
import com.bill.services.BillingService;
import com.bill.strategy.ItemMergeStrategy;

@Service
public class BillingServiceImpl implements BillingService {
	@Autowired
	private BillsRepositoy billsRepositoy;

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

	@Override
	public BillsEntity checkout(Bills bills) {
		if (bills.isEmpty()) {
			throw new IllegalStateException("Empty bill");
		}

		BillsEntity entity = new BillsEntity();

		double total = 0;

		for (OrderItem item : bills.getItems()) {

			OrderItemEntity itemEntity = new OrderItemEntity();
			itemEntity.setItemName(item.getName());
			itemEntity.setPrice(item.getPrice());
			itemEntity.setQuantity(item.getQuantity());
			itemEntity.setQuantityPrice(item.getTotalPrice());

			entity.addItem(itemEntity);

			total += item.getTotalPrice();
		}

		entity.setTotalAmount(total);
		entity.setFinalizedAt(LocalDateTime.now());

		return billsRepositoy.save(entity);
	}
}
