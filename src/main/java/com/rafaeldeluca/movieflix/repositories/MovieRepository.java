package com.rafaeldeluca.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaeldeluca.movieflix.entities.Genre;
import com.rafaeldeluca.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{	
	
	
	@Query("SELECT DISTINCT objeto "
			+ "FROM Movie objeto "
			+ "WHERE (:genre IS NULL OR :genre = objeto.genre) "
			+ "ORDER BY objeto.title")
	Page<Movie> busca(Genre genre, Pageable pageable);

}
