package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.OrderTable;
import com.example.demo.exception.NoResultFoundException;
import com.example.demo.repository.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/orders")
	public List<OrderTable> getAllOrder() {
		List<OrderTable> orderList=orderRepository.findAll();
		if(orderList.isEmpty()) {
			throw new NoResultFoundException("No result found");
		}
		return orderList;
	}
	
	@GetMapping("/order/{orderNo}")
	public OrderTable getOrder(@PathVariable int orderNo) {
		Optional<OrderTable> order=orderRepository.findById(orderNo);
		return (order.isPresent())?order.get():null;
	}
	
	@GetMapping("/order")
	public OrderTable getOrderByStoreId(@RequestParam int storeId) {
		Optional<OrderTable> order=Optional.ofNullable(orderRepository.findByStoreId(storeId));
		return (order.isPresent())?order.get():null;
	}
	
	@GetMapping("/order/byStoreId")
	public OrderTable getCustomerByStoreId(@RequestParam int custId) {
		Optional<OrderTable> order=Optional.ofNullable(orderRepository.findByCustomerId(custId));
		return (order.isPresent())?order.get():null;
	}
	
	@GetMapping("/order/byStatus")
	public List<OrderTable> getOrderByStatus(@RequestParam String orderStatus) {
		List<OrderTable> order=orderRepository.findByOrderStatus(orderStatus);
		return order;
	}
	
	@PostMapping("/order")
	public String saveOrder(@RequestBody OrderTable order) {
		orderRepository.save(order);
		return "saved";
	}
	
	
}
