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

import com.example.couser.entities.User;
import com.example.couser.repositories.UseRepository;
import com.example.couser.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UseRepository useRepository;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> user = new UserService().findAll();
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findBy(@PathVariable Long id) {
		return userService.findBy(id);
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user) {
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<User> findById = useRepository.findById(id);

		if (findById.isPresent()) {
			userService.delete(id);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User user) {
		return useRepository.findById(user.getId())
				.map(x -> ResponseEntity.status(HttpStatus.CREATED).body(userService.update(user)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
