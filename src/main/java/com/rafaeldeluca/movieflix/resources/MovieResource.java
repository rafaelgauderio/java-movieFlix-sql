package com.rafaeldeluca.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaeldeluca.movieflix.services.MovieService;
import com.rafaeldeluca.movieflix.services.ReviewService;
import com.rafaeldeluca.movifliex.dto.MovieDTO;
import com.rafaeldeluca.movifliex.dto.ReviewDTO;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
	
	@Autowired
	private MovieService service;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAll (
			@RequestParam(value = "page", defaultValue="0") Integer page,
			@RequestParam(value= "linesPerPage", defaultValue="12") Integer linerPerPage,
			@RequestParam(value ="direction", defaultValue="ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue="title") String orderBy
			) {
		
		PageRequest pagerequest = PageRequest.of(page, linerPerPage, Direction.valueOf(direction), orderBy);
		Page<MovieDTO> paginatedList = service.findAllPaged(pagerequest);
		return ResponseEntity.ok().body(paginatedList);	
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<MovieDTO>findById(@PathVariable Long id) {
		MovieDTO movieDTO = service.findById(id);
		return ResponseEntity.ok().body(movieDTO);
	}
	
	@GetMapping(value="/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> findMovieReviews(@PathVariable Long movieId) {
		List<ReviewDTO> lista = reviewService.findByMovie(movieId);
		return ResponseEntity.ok(lista);
	}

}
