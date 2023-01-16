package com.rafaeldeluca.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaeldeluca.movieflix.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
