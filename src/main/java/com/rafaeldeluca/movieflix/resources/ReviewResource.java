package com.rafaeldeluca.movieflix.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaeldeluca.movieflix.services.ReviewService;
import com.rafaeldeluca.movifliex.dto.ReviewDTO;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {
	
	@Autowired
	private ReviewService service;	
	
	@PreAuthorize("hasAnyRole('MEMBER')")
	@PostMapping
	public ResponseEntity<ReviewDTO> insertReview(@Valid @RequestBody ReviewDTO reviewDTO) {
		
		reviewDTO = service.insert(reviewDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(reviewDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(reviewDTO);	
		
	}
	
	
	@PreAuthorize("permitAll()") 
	@GetMapping
	public ResponseEntity<List <ReviewDTO>> findAll () {
		
		List<ReviewDTO> listaDTO = service.findAll();
		return ResponseEntity.ok().body(listaDTO);
		
	}

}
