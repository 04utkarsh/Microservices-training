package com.example.demo.controllers;

import com.example.demo.entity.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderItems;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer>{
    public List<OrderItems> findByOrderNo(int orderNo);
}
