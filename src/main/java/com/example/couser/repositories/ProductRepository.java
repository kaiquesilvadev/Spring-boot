package com.example.couser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couser.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	
}
