package com.rafaeldeluca.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaeldeluca.movieflix.entities.Movie;
import com.rafaeldeluca.movieflix.entities.Review;
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

	public List<ReviewDTO> findByMovie(Long movieId) {

		try {
			Movie movie = movieRepository.getOne(movieId);
			List<Review> lista = reviewRepository.findByMovie(movie);
			return lista.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		} catch (EntityNotFoundException error) {
			throw new ResourceNotFoundException("Id desse filme não foi encontrda" + movieId);
		}

	}


}
