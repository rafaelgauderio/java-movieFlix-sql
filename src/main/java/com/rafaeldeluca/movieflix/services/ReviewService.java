package com.rafaeldeluca.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.Movie;
import com.rafaeldeluca.movieflix.entities.Review;
import com.rafaeldeluca.movieflix.entities.User;
import com.rafaeldeluca.movieflix.repositories.MovieRepository;
import com.rafaeldeluca.movieflix.repositories.ReviewRepository;
import com.rafaeldeluca.movieflix.services.exceptions.ResourceNotFoundException;
import com.rafaeldeluca.movifliex.dto.ReviewDTO;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public List<ReviewDTO> findByMovie(Long movieId) {

		try {
			Movie movie = movieRepository.getOne(movieId);
			List<Review> lista = reviewRepository.findByMovie(movie);
			return lista.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		} catch (EntityNotFoundException error) {
			throw new ResourceNotFoundException("Id desse filme não foi encontrado" + movieId);
		}

	}

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {

		User usuario = authService.authenticated();

		try {
			
			Movie movie = movieRepository.getOne(dto.getMovieId());
			Review entity = new Review();
			
			// entity.setId(dto.getId()); id é autoincrement
			entity.setMovie(movie);
			entity.setUser(usuario);
			entity.setText(dto.getText());			
			
			entity = reviewRepository.save(entity);
			return new ReviewDTO(entity);
		} catch (EntityNotFoundException error) {
			throw new ResourceNotFoundException("Id do movie informado não foi encontrada " + dto.getMovieId());
		}

	}
	
	@Transactional(readOnly=true)
	public List<ReviewDTO> findAll () {
		List<Review> lista = reviewRepository.findAll();
		return lista.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		
	}

}
