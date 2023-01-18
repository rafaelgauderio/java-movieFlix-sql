package com.rafaeldeluca.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.Genre;
import com.rafaeldeluca.movieflix.repositories.GenreRepository;
import com.rafaeldeluca.movieflix.services.exceptions.ResourceNotFoundException;
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
	
	@Transactional(readOnly=true)
	public GenreDTO findById(Long id) {
		Optional<Genre> objeto = repository.findById(id);
		Genre entity = objeto.orElseThrow(() -> new ResourceNotFoundException("Genero não encontrado com o id informado: " + id));
		GenreDTO dto = new GenreDTO(entity);
		return dto;	
		
	}

}
