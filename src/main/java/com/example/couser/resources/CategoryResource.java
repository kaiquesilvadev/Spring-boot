package com.example.couser.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.couser.entities.Category;
import com.example.couser.repositories.CategoryRepository;
import com.example.couser.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

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
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).body(category);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Category> findById = categoryRepository.findById(id);

		if (findById.isPresent()) {
			categoryService.delete(id);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@RequestBody Category category) {
		return categoryRepository.findById(category.getId())
				.map(x -> ResponseEntity.status(HttpStatus.CREATED).body(categoryService.update(category)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
