package com.rafaeldeluca.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.Movie;
import com.rafaeldeluca.movieflix.repositories.MovieRepository;
import com.rafaeldeluca.movifliex.dto.MovieDTO;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPaged(PageRequest pageRequest) {
		Page <Movie> paginatedList = repository.findAll(pageRequest);
		return paginatedList.map(x -> new MovieDTO (x));
	}

}
