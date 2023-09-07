package com.example.couser.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couser.config.services.OrderService;
import com.example.couser.entities.Order;

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
}
