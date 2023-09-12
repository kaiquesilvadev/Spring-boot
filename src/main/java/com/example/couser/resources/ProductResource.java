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

import com.example.couser.entities.Product;
import com.example.couser.repositories.ProductRepository;
import com.example.couser.services.ProductService;

@RestController
@RequestMapping("/productes")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> product = new ProductService().findAll();
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findBy(@PathVariable Long id) {
		return productService.findBy(id);
	}

	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		product = productService.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Product> findById = productRepository.findById(id);

		if (findById.isPresent()) {
			productService.delete(id);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		return productRepository.findById(product.getId())
				.map(x -> ResponseEntity.status(HttpStatus.CREATED).body(productService.update(product)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
