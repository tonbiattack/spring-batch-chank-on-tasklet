package com.example.springbatch.chankontasklet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatch.chankontasklet.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
