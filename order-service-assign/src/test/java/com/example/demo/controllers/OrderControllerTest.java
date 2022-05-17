package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.OrderItems;
import com.example.demo.entity.OrderSortingAttribute;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.OrderTable;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;

//@WebMvcTest(OrderController.class)
@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

	@InjectMocks
	private OrderController orderController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private OrderItemsRepository orderItemsRepository;
	
	List<OrderTable> listOrderTable =Arrays.asList(
			new OrderTable(1,1,"store",null,100L,10,OrderStatus.PLACED),
			new OrderTable(2,2,"store2",null,200L,20,OrderStatus.CANCELLED),
			new OrderTable(3,3,"store3",null,300L,30,OrderStatus.SHIPPED));
	
	@Test
	void testGetAllOrder() {
		when(orderRepository.findAll()).thenReturn(listOrderTable);
		
		List<OrderTable> list=orderController.getAllOrder();
		assertEquals(1,list.get(0).getOrderNo());
		assertEquals(OrderStatus.SHIPPED,list.get(2).getOrderStatus());
	}
	
	@Test
	void testGetAllOrderNegativeCase() throws Exception{
		when(orderRepository.findAll()).thenReturn(new ArrayList<>());
		List<OrderTable> list=new ArrayList<>();
		try {
		list=orderController.getAllOrder();
		}catch(Exception ex) {
			assertEquals("No result found",ex.getMessage());
		}
		
		assertEquals(0,list.size());
	}
	
	@Test
	void testGetOrderById() {
		when(orderRepository.findById(1)).thenReturn(Optional.of(listOrderTable.get(0)));
		
		OrderTable orderTable=orderController.getOrder(1);
		
		assertEquals(1,orderTable.getOrderNo());
		assertEquals(1,orderTable.getStoreId());
	}
	
	@Test
	void testGetOrderByStoreId(){
		when(orderRepository.findByStoreId(1)).thenReturn(listOrderTable);
		
		List<OrderTable> orderTable=orderController.getOrderByStoreId(1);
		
		assertEquals(3,orderTable.size());
	}
	
	@Test
	void testGetOrderByCustomerId(){
		when(orderRepository.findByCustomerId(10)).thenReturn(listOrderTable);
		
		List<OrderTable> orderTable=orderController.getOrderByCustomerId(10);
		
		assertEquals(3,orderTable.size());
	}
	
	@Test
	void testGetOrderByStatus(){
		when(orderRepository.findByOrderStatus("CANCELLED")).thenReturn(Arrays.asList(listOrderTable.get(1)));
		
		List<OrderTable> orderTable=orderController.getOrderByStatus("CANCELLED");
		
		assertEquals(1,orderTable.size());
		assertEquals(2,orderTable.get(0).getStoreId());
	}
	
	@Test
	void testGetSortByStatus(){
		when(orderRepository.findAllByOrderByOrderStatusAsc()).thenReturn(Arrays.asList(listOrderTable.get(1)));
		
		List<OrderTable> orderTable=orderController.getSortByStatus(OrderSortingAttribute.STATUS);
		
		assertEquals(1,orderTable.size());
		assertEquals(2,orderTable.get(0).getStoreId());
	}
	
//	//@Test
//	void testGetOrderByPagination(){
//		Pageable firstPageWithPageSizeElements = PageRequest.of(0, 1);
//		Page<OrderTable> order =null;
//		when(orderRepository.findAll(firstPageWithPageSizeElements)).thenReturn(order);
//		
//		List<OrderTable> orderTable=orderController.getOrderByPagination(0,1);
//		
//		assertEquals(0,orderTable.size());
////		assertEquals(2,orderTable.get(0).getStoreId());
//	}
	
	@Test
	void testSaveOrder(){
		when(orderRepository.save(listOrderTable.get(0))).thenReturn(null);
		
		String actual=orderController.saveOrder(listOrderTable.get(0));
		
		assertEquals("saved",actual);
	}
	
	@Test
	void testSaveOrderItems(){
		//int orderNo, int productId, int quantity, int price, OrderStatus status
		OrderTable orderTable=listOrderTable.get(0);
		OrderItems orderItems=new OrderItems(1,10,100,1000,OrderStatus.CLOSED);
		orderTable.setOrderItemsList(Arrays.asList(orderItems));
		//when(orderRepository.findById(1)).thenReturn(Optional.of(orderTable));
		
		String actual=orderController.saveOrderItems(orderItems);
		
		assertEquals("saved",actual);
	}
	
	@Test
	void testUpdateOrder(){
		when(orderRepository.findById(1)).thenReturn(Optional.of(listOrderTable.get(0)));
		OrderItems orderItems=new OrderItems(1,10,100,1000,OrderStatus.CLOSED);
		when(orderItemsRepository.findById(1)).thenReturn(Optional.of(orderItems));
		
		String orderTable=orderController.updateOrder(1);
		
		assertEquals("updated",orderTable);
//		assertEquals(2,orderTable.get(0).getStoreId());
	}
	
	@Test
	void testGetterMethods() {
		OrderTable orderTable=listOrderTable.get(0);
		long actualAmount=orderTable.getOrderAmount();
		int actualOrderNo=orderTable.getOrderNo();
		int actualCustomerId=orderTable.getCustomerId();
		Date actualdate=orderTable.getDated();
		OrderStatus actualOrderStatus=orderTable.getOrderStatus();
		int actualStoreId=orderTable.getStoreId();
		String actualStoreName=orderTable.getStoreName();
		List<OrderItems> orderItemsList=orderTable.getOrderItemsList();
		
		assertEquals(100L,actualAmount);
		assertEquals(1,actualOrderNo);
		assertEquals(10,actualCustomerId);
		assertEquals(null,actualdate);
		assertEquals(OrderStatus.PLACED,actualOrderStatus);
		assertEquals(1,actualStoreId);
		assertEquals("store",actualStoreName);
	}
	
	@Test
	void testGetterMethodsForOrderItems() {
		OrderItems orderItems=new OrderItems(1,10,100,1000,OrderStatus.CLOSED);
		int actualOrderItems=orderItems.getOrderNo();
		int actualPrice=orderItems.getPrice();
		int actualProductId=orderItems.getProductId();
		int actualQuantity=orderItems.getQuantity();
		OrderStatus actualOrderStatus=orderItems.getStatus();
		Product product = orderItems.getProduct();
		
		
		assertEquals(1,actualOrderItems);
		assertEquals(1000,actualPrice);
		assertEquals(10,actualProductId);
		assertEquals(100,actualQuantity);
		assertEquals(OrderStatus.CLOSED,actualOrderStatus);
	}
	
	@Test
	void testSetterMethods() {
		OrderTable orderTable=new OrderTable(1,1,"store",null,100L,10,OrderStatus.PLACED);
		OrderItems orderItems1=new OrderItems(1,10,100,1000,OrderStatus.CLOSED);
		OrderItems orderItems2=new OrderItems(2,20,200,2000,OrderStatus.CLOSED);
		orderTable.setOrderAmount(1000L);
		orderTable.setOrderNo(2);
		orderTable.setCustomerId(20);
		orderTable.setOrderStatus(OrderStatus.CANCELLED);
		orderTable.setStoreId(3);
		orderTable.setStoreName("store2");
		orderTable.setOrderItemsList(Arrays.asList(orderItems1));
		orderItems2.setOrderItemId(3);
		
		assertEquals(1000L,orderTable.getOrderAmount());
		assertEquals(2,orderTable.getOrderNo());
		assertEquals(20,orderTable.getCustomerId());
		assertEquals(OrderStatus.CANCELLED,orderTable.getOrderStatus());
		assertEquals(3,orderTable.getStoreId());
		assertEquals("store2",orderTable.getStoreName());
	}
	
	@Test
	void testSetterMethodsForOrderItems() {
		OrderItems orderItems=new OrderItems(1,10,100,1000,OrderStatus.CLOSED);
		orderItems.setOrderNo(2);
		orderItems.setPrice(2000);
		orderItems.setQuantity(200);
		orderItems.setProductId(20);
		orderItems.setStatus(OrderStatus.CANCELLED);
		int actualPrice=orderItems.getPrice();
		int actualProductId=orderItems.getProductId();
		int actualQuantity=orderItems.getQuantity();
		OrderStatus actualOrderStatus=orderItems.getStatus();
		
		Product product=new Product(1,"product1",1,100,true);
		orderItems.setProduct(product);
		
		assertEquals(2,orderItems.getOrderNo());
		assertEquals(2000,actualPrice);
		assertEquals(20,actualProductId);
		assertEquals(200,actualQuantity);
		assertEquals(OrderStatus.CANCELLED,actualOrderStatus);
	}
}
