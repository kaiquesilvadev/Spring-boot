package com.example.couser.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.couser.entities.Category;
import com.example.couser.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public ResponseEntity<Category> findBy(Long id) {
		return categoryRepository.findById(id).map(x -> ResponseEntity.ok(x))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	public Category insert(Category category) {
		return categoryRepository.save(category);
	}

	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

	public Category update(Category category) {
		return categoryRepository.save(category);
	}
}
