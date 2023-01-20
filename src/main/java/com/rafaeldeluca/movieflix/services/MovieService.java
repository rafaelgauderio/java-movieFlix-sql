package com.rafaeldeluca.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.Genre;
import com.rafaeldeluca.movieflix.entities.Movie;
import com.rafaeldeluca.movieflix.repositories.GenreRepository;
import com.rafaeldeluca.movieflix.repositories.MovieRepository;
import com.rafaeldeluca.movieflix.services.exceptions.ResourceNotFoundException;
import com.rafaeldeluca.movifliex.dto.MovieDTO;
import com.rafaeldeluca.movifliex.dto.MovieSimpleDTO;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPaged(PageRequest pageRequest) {
		Page <Movie> paginatedList = repository.findAll(pageRequest);
		return paginatedList.map(x -> new MovieDTO (x));
	}
	
	
	/*
	@Transactional(readOnly = true)
	public Optional<Object> findByGenre(Long id) {
		Optional<Movie> paginatedList = repository.findById(id);
		return paginatedList.map(x -> new MovieSimpleDTO (x));
	}
	*/
	
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		
		Optional<Movie> objeto = repository.findById(id);
		Movie entity = objeto.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada esse id de filme: " + id));
		return new MovieDTO(entity);
			
	}
	
	@Transactional(readOnly = true)
	public Page<MovieSimpleDTO> buscaTodasPaginas (Long genreId, Pageable pageble) {
		Genre genre = (genreId==0) ? null : genreRepository.getOne(genreId);
		Page<Movie> listaPaginada = repository.busca(genre, pageble);
		return listaPaginada.map( x -> new MovieSimpleDTO(x));
	}
	

}
