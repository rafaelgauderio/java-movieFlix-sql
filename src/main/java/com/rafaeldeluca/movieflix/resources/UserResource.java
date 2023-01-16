package com.rafaeldeluca.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaeldeluca.movieflix.services.UserService;
import com.rafaeldeluca.movifliex.dto.UserDTO;

@RestController
@RequestMapping(value = "/users/profile")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<UserDTO> returnUserProfile() {
		UserDTO dto = service.returnUserProfile();
		return ResponseEntity.ok().body(dto);
	}

}
