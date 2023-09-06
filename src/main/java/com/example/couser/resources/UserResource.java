package com.example.couser.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couser.config.services.UserService;
import com.example.couser.entities.User;
import com.example.couser.repositories.UseRepository;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UseRepository repository;

	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> user = new UserService().findAll();
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findBy(@PathVariable Long id) {
	         return userService.findBy(id);
	}
}
