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

import com.example.couser.entities.Order;
import com.example.couser.repositories.OrderRepository;
import com.example.couser.services.OrderService;

@RestController
@RequestMapping("/ordes")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> order = new OrderService().findAll();
		return ResponseEntity.ok().body(order);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> findBy(@PathVariable Long id) {
		return orderService.findBy(id);
	}

	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody Order order) {
		order = orderService.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(order);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Order> findById = orderRepository.findById(id);

		if (findById.isPresent()) {
			orderService.delete(id);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> update(@RequestBody Order order) {
		return orderRepository.findById(order.getId())
				.map(x -> ResponseEntity.status(HttpStatus.CREATED).body(orderService.update(order)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
