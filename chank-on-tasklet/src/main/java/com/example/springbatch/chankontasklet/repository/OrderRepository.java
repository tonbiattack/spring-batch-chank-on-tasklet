package com.example.springbatch.chankontasklet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatch.chankontasklet.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
