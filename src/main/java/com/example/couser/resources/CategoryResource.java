package com.example.couser.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couser.config.services.CategoryService;
import com.example.couser.entities.Category;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> category = new CategoryService().findAll();
		return ResponseEntity.ok().body(category);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findBy(@PathVariable Long id) {
		return categoryService.findBy(id);
	}
}
