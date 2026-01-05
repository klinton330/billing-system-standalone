package com.bill.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bill.entity.BillsEntity;

public interface BillsRepositoy extends JpaRepository<BillsEntity, Integer> {

}
