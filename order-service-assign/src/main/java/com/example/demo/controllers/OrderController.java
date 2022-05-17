package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.OrderItems;
import com.example.demo.entity.OrderSortingAttribute;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.OrderTable;
import com.example.demo.exception.NoResultFoundException;
import com.example.demo.repository.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	
	@GetMapping("/orders/all")
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
		if(order.isEmpty()) {
			throw new NoResultFoundException("No data found with given orderID");
		}
		return order.get();
	}
	
	@GetMapping("/order/byStoreId")
	public List<OrderTable> getOrderByStoreId(@RequestParam int storeId) {
		Optional<List<OrderTable>> order=Optional.ofNullable(orderRepository.findByStoreId(storeId));
		if(order.isEmpty()) {
			throw new NoResultFoundException("No data found with given storeID");
		}
		return order.get();
	}
	
	@GetMapping("/order/byCustomerId")
	public List<OrderTable> getOrderByCustomerId(@RequestParam int custId) {
		Optional<List<OrderTable>> order=Optional.ofNullable(orderRepository.findByCustomerId(custId));
		if(order.isEmpty()) {
			throw new NoResultFoundException("No data found with given customerID");
		}
		return order.get();
	}
	
	@GetMapping("/order/byStatus")
	public List<OrderTable> getOrderByStatus(@RequestParam String status) {
		List<OrderTable> orders = orderRepository.findByOrderStatus(status);
		if(orders.isEmpty()) {
			throw new NoResultFoundException("No data found with given customerID");
		}
		return orders;
	}
	
	@GetMapping("/order/sortBy")
	public List<OrderTable> getSortByStatus(@RequestParam OrderSortingAttribute sort) {
		List<OrderTable> order=new ArrayList<>();
		if(String.valueOf(sort).equals("STATUS")) {
			order=orderRepository.findAllByOrderByOrderStatusAsc();
		}else if(String.valueOf(sort).equals("DATE")) {
			order=orderRepository.findAllByOrderByDatedAsc();
		}
		
		return order;
	}
	
	@GetMapping("/order/pagination")
	public List<OrderTable> getOrderByPagination(@RequestParam int pageNo,int pagesize) {
		Pageable firstPageWithPageSizeElements = PageRequest.of(pageNo, pagesize);
		Page<OrderTable> order = orderRepository.findAll(firstPageWithPageSizeElements);
		return order.getContent();
	}
	
	@PostMapping("/order")
	@ResponseStatus(HttpStatus.CREATED)
	public String saveOrder(@Valid @RequestBody OrderTable order) {
		orderRepository.save(order);
		return "saved";
	}
	
	@PutMapping("/order/cancel/{orderNo}")
	public String updateOrder(@PathVariable int orderNo) {
		Optional<OrderTable> orderTable=orderRepository.findById(orderNo);
		if(orderTable.isPresent()) {
			OrderTable order=orderTable.get();
			order.setOrderStatus(OrderStatus.CANCELLED);
			List<OrderItems> orderItems = orderItemsRepository.findByOrderNo(orderNo);
			for(OrderItems orderItem : orderItems){
				orderItem.setStatus(OrderStatus.CANCELLED);
				orderItemsRepository.save(orderItem);
			}
			orderRepository.save(order);
		}
		return "updated";
	}
	
	@PostMapping("/orderitems")
	public String saveOrderItems(@Valid @RequestBody OrderItems orderItems) {
		Optional<OrderTable> orderTable=orderRepository.findById(orderItems.getOrderNo());
		if(orderTable.isPresent()) {
			OrderTable order=orderTable.get();
			order.addSingleOrderItems(orderItems);
			orderRepository.save(order);
		}
		return "saved";
	}
	
}

	