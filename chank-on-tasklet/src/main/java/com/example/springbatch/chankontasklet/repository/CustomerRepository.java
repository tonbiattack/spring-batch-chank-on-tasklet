package com.example.springbatch.chankontasklet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatch.chankontasklet.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
