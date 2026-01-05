package com.bill.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class BillsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double totalAmount;

	private LocalDateTime finalizedAt;

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
	private List<OrderItemEntity> items = new ArrayList<>();

	public void addItem(OrderItemEntity item) {
		item.setBill(this);
		items.add(item);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getFinalizedAt() {
		return finalizedAt;
	}

	public void setFinalizedAt(LocalDateTime finalizedAt) {
		this.finalizedAt = finalizedAt;
	}

	public List<OrderItemEntity> getItems() {
		return items;
	}

	public void setItems(List<OrderItemEntity> items) {
		this.items = items;
	}
	
	

}
