package com.bill.util;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bill.entity.Bills;
import com.bill.entity.Menu;
import com.bill.services.BillingService;

@Component
public class BillingUtils {

	@Autowired
	BillingService billingService;

	public void addItemFlow(Map<Integer, Menu> menu, Bills bills, Scanner sc) {

		System.out.println("\n--- MENU ---");

		menu.forEach((id, item) -> System.out.println(id + ". " + item.getName() + " - ₹" + item.getPrice()));

		while (true) {

			System.out.println("0. Done adding items");

			System.out.print("Enter item number: ");
			int itemNo = sc.nextInt();

			if (itemNo == 0) {
				System.out.println("Finished adding items");
				break;
			}

			Menu menuitem = menu.get(itemNo);
			if (menuitem == null) {
				System.out.println("Invalid menu number");
				continue;
			}

			System.out.print("Enter quantity: ");
			int qty = sc.nextInt();

			if (qty <= 0) {
				System.out.println("Quantity must be greater than zero");
				continue;
			}

			billingService.addItem(bills, menuitem, qty);
			System.out.println("Added: " + menuitem.getName() + " x " + qty);
		}

	}

	public void viewBill(Bills bill) {

		System.out.println("\n--- CURRENT BILL ---");

		if (bill.getItems().isEmpty()) {
			System.out.println("No items added yet");
			return;
		}

		bill.getItems().forEach(item -> System.out
				.println(item.getName() + " x " + item.getQuantity() + " = ₹" + item.getTotalPrice()));

		System.out.println("Total: ₹" + billingService.calculateTotal(bill));
	}

	public void editBills(Map<Integer, Menu> menu, Bills bills, Scanner sc) {

		System.out.println("\n--- MENU ---");

		menu.forEach((id, item) -> System.out.println(id + ". " + item.getName() + " - ₹" + item.getPrice()));

		while (true) {

			System.out.println("0. Done adding items");
			if (bills.isEmpty()) {
				System.out.println("Bill is empty");
				return;
			}
			System.out.print("Enter item number to edit: ");
			int itemNo = sc.nextInt();

			if (itemNo == 0) {
				System.out.println("Finished adding items");
				break;
			}

			Menu menuItem = menu.get(itemNo);
			if (menuItem == null) {
				System.out.println("Invalid menu number");
				continue;
			}

			System.out.print("Enter new quantity: ");
			int qty = sc.nextInt();

			if (qty <= 0) {
				System.out.println("Quantity must be greater than zero");
				return;
			}
			
			try {
				billingService.updateQuantity(bills, menuItem, qty);
		        System.out.println("Updated: " + menuItem.getName()+" "+menuItem.getPrice());
		    } catch (IllegalArgumentException ex) {
		        System.out.println(ex.getMessage());
		    }
		}
	}

	

}
