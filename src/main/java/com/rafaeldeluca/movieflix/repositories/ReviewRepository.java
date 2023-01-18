package com.rafaeldeluca.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaeldeluca.movieflix.entities.Genre;
import com.rafaeldeluca.movieflix.entities.Movie;
import com.rafaeldeluca.movieflix.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByMovie(Movie movie);

}
