package com.example.couser.config.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.couser.entities.User;
import com.example.couser.repositories.UseRepository;

@Service
public class UserService {

	@Autowired
	private UseRepository useRepository;

	public List<User> findAll() {
		return useRepository.findAll();
	}

	public ResponseEntity<User> findBy(Long id) {
		return useRepository.findById(id).map(x -> ResponseEntity.ok(x))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	public User insert(User user) {
		return useRepository.save(user);
	}
}
