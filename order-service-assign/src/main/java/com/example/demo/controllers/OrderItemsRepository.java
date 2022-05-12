package com.example.demo.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer>{

}
