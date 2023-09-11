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

import com.example.couser.config.services.OrderService;
import com.example.couser.entities.Order;
import com.example.couser.entities.User;

@RestController
@RequestMapping("/ordes")
public class OrderResource {

	@Autowired
	private OrderService orderService;

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
}
