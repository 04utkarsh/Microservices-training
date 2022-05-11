package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ordertable;
import com.example.demo.repository.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/order/{orderNo}")
	public Ordertable getOrder(@PathVariable int orderNo) {
		Optional<Ordertable> order=orderRepository.findById(orderNo);
		return order.get();
	}
	
	@PostMapping("/order")
	public String saveOrder(@RequestBody Ordertable order) {
		orderRepository.save(order);
		return "saved";
	}
	
	
}
