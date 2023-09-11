package com.example.couser.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.couser.config.services.CategoryService;
import com.example.couser.entities.Category;
import com.example.couser.entities.User;

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
	
	@PostMapping
	public ResponseEntity<Category> insert(@RequestBody Category category) {
		category = categoryService.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
	}
}
