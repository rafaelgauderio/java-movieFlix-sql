package com.rafaeldeluca.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.Genre;
import com.rafaeldeluca.movieflix.repositories.GenreRepository;
import com.rafaeldeluca.movifliex.dto.GenreDTO;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository repository;
	
	@Transactional(readOnly=true)
	public List<GenreDTO> findAll () {
		List<Genre> lista = repository.findAll();
		return lista.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
		
	}	

}
