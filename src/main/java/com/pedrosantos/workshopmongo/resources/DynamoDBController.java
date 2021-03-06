package com.pedrosantos.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrosantos.workshopmongo.domain.User;
import com.pedrosantos.workshopmongo.repository.DynamoDBRepository;

@RestController
@RequestMapping("/dynamodb")
public class DynamoDBController {

	@Autowired
	private DynamoDBRepository repository;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody User user) {
		repository.insertIntoDynamoDB(user);

		return ResponseEntity.created(null).build();
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<User> index(@PathVariable String userId, @RequestParam String email) {
		User user = repository.getUserDetails(userId, email);

		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<List<User>> index() {

		List<User> users = repository.getAllUsers();

		return ResponseEntity.ok(users);
	}

	@PutMapping
	public void update(@RequestBody User user) {
		repository.updateUserDetails(user);
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestParam String userId, @RequestParam String email) {
		User user = new User();

		user.setId(userId);
		user.setEmail(email);

		Boolean deleted = repository.deleteUserDetails(user);

		if (deleted)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.badRequest().build();
	}
}
