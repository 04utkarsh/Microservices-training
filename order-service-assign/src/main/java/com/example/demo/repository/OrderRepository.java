package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderTable;

@Repository
public interface OrderRepository extends JpaRepository<OrderTable,Integer>{

	public List<OrderTable> findByOrderStatus(String orderStatus);
	public List<OrderTable> findByStoreId(int storeId);
	public List<OrderTable> findByCustomerId(int custId);
	public List<OrderTable> findAllByOrderByOrderStatusAsc();
	public List<OrderTable> findAllByOrderByDatedAsc();
}
