package com.bill.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItemEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private double price;
    private int quantity;
    private double quantityPrice;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private BillsEntity bill;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BillsEntity getBill() {
		return bill;
	}

	public void setBill(BillsEntity bill) {
		this.bill = bill;
	}
	public double getQuantityPrice() {
		return quantityPrice;
	}
	public void setQuantityPrice(double quantityPrice) {
		this.quantityPrice = quantityPrice;
	}
    
}
