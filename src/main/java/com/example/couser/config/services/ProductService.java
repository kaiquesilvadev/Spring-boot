package com.example.couser.config.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.couser.entities.Product;
import com.example.couser.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public ResponseEntity<Product> findBy(Long id) {
		return productRepository.findById(id).map(x -> ResponseEntity.ok(x))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	public Product insert(Product  product) {
		return productRepository.save(product);
	}
}
