package com.example.couser.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.couser.entities.Order;
import com.example.couser.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public ResponseEntity<Order> findBy(Long id) {
		return orderRepository.findById(id).map(x -> ResponseEntity.ok(x))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	public Order insert(Order  order) {
		return orderRepository.save(order);
	}
	
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
}
