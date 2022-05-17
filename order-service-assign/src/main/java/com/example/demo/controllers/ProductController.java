package com.example.demo.controllers;

import java.util.Optional;

import com.example.demo.exception.NoResultFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/product/{prodId}")
	public Product getProductById(@PathVariable int prodId) {
		Optional<Product> product=productRepository.findById(prodId);
		if(product.isEmpty()){
			throw new NoResultFoundException("No result found with given ProductId");
		}
		return product.get();
	}
}
