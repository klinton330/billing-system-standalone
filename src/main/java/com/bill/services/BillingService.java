package com.bill.services;

import com.bill.entity.Bills;
import com.bill.entity.Menu;

public interface BillingService {
	public void addItem(Bills bills, Menu menu, int qty);
	public double calculateTotal(Bills bill);
	public void updateQuantity(Bills bills, Menu menuItem, int qty);
}
