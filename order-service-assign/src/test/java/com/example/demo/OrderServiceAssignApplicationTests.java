package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceAssignApplicationTests {

	@Test
	void contextLoads() {
		
		System.out.println("this is test file");
		int a=1,b=1;
		assertEquals(2,a+b);
	}

}
