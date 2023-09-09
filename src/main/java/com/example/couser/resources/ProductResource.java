package com.example.couser.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couser.config.services.ProductService;
import com.example.couser.entities.Category;
import com.example.couser.entities.Product;

@RestController
@RequestMapping("/productes")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> product = new ProductService().findAll();
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findBy(@PathVariable Long id) {
		return productService.findBy(id);
	}
}
