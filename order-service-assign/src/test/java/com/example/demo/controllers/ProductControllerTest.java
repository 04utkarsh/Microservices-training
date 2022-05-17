package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.OrderTable;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@InjectMocks
	private ProductController productController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private ProductRepository productRepository;
	
	//int prodId, String productName, int rating, int price, boolean isActive
	List<Product> listProduct =Arrays.asList(
			new Product(1,"product1",1,100,true),
			new Product(2,"product2",2,200,true),
			new Product(3,"product3",3,300,false));
	
	@Test
	void testGetProductById() {
		when(productRepository.findById(1)).thenReturn(Optional.of(listProduct.get(0)));
		
		Product product=productController.getProductById(1);
		
		assertEquals(1,product.getProdId());
		assertEquals(100,product.getPrice());
	}
	
	@Test
	void testSetterMethods() {
		Product product=new Product(1,"product1",1,100,true);
		product.setPrice(200);
		product.setProdId(2);
		product.setRating(5);
		product.setProductName("product2");
		product.setOrderItems(null);
		product.setActive(false);
		
		assertEquals(200,product.getPrice());
		assertEquals(2,product.getProdId());
		assertEquals(5,product.getRating());
		assertEquals("product2",product.getProductName());
		assertEquals(null,product.getOrderItems());
		assertEquals(false,product.isActive());
	}
	
	@Test
	void testGetterMethods() {
		Product product=listProduct.get(0);
		int actualPrice=product.getPrice();
		int actualProdId=product.getProdId();
		int actualRating=product.getRating();
		String actualProductName=product.getProductName();
		OrderItems actualOrderItems=product.getOrderItems();
		boolean actualIsActive=product.isActive();
		
		assertEquals(100,actualPrice);
		assertEquals(1,actualProdId);
		assertEquals(1,actualRating);
		assertEquals("product1",actualProductName);
		assertEquals(null,actualOrderItems);
		assertEquals(true,actualIsActive);
	}
	
	}
