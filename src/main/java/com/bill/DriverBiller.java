package com.bill;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bill.entity.Bills;
import com.bill.entity.Menu;
import com.bill.service.impl.MenuServiceImpl;
import com.bill.util.BillingUtils;

@Component
public class DriverBiller {
	@Autowired
	MenuServiceImpl menuServiceImpl;

	@Autowired
	BillingUtils billingUtils;

	Bills bills = new Bills();

	public void execute() {
		System.out.println("Welcome to Raju Chats");
		Map<Integer, Menu> menu = menuServiceImpl.getMenu();
		System.out.println("\n--- MENU LIST ---");

		if (menu.isEmpty()) {
			System.out.println("Menu is empty");
			return;
		}

		while (true) {
			System.out.println("""
					1. Add Item
					2. View Bill
					3. Edit Item
					4. Finalize & Exit
					""");

			System.out.print("Choose option: ");
			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();

			switch (option) {
			case 1:
				billingUtils.addItemFlow(menu, bills, sc);
				break;
			case 2:
				billingUtils.viewBill(bills);
				break;
			case 3:
				billingUtils.editBills(menu, bills, sc);
				break;
			case 4:
				billingUtils.saveToDB(bills);
				bills =new Bills();
				break;

			}

		}

	}

}
