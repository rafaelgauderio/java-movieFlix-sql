package com.rafaeldeluca.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.Genre;
import com.rafaeldeluca.movieflix.repositories.GenreRepository;
import com.rafaeldeluca.movieflix.services.exceptions.DataBaseException;
import com.rafaeldeluca.movieflix.services.exceptions.ResourceNotFoundException;
import com.rafaeldeluca.movifliex.dto.GenreDTO;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;

	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {
		List<Genre> lista = repository.findAll();
		return lista.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public GenreDTO findById(Long id) {
		Optional<Genre> objeto = repository.findById(id);
		Genre entity = objeto
				.orElseThrow(() -> new ResourceNotFoundException("Genero não encontrado com o id informado: " + id));
		GenreDTO dto = new GenreDTO(entity);
		return dto;

	}

	@Transactional(readOnly = false) // update e save tem que poder alterar o database
	public GenreDTO update(Long id, GenreDTO dto) {

		try {
			Genre entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new GenreDTO(entity);
		} catch (EntityNotFoundException error) {

			throw new ResourceNotFoundException("Não foi encontrada o gênero para o id informado: " + id);
		}
	}

	@Transactional(readOnly = false) // update e save tem que poder alterar o database
	public GenreDTO insert(GenreDTO dto) {

		Genre entity = new Genre();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new GenreDTO(entity);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException error) {
			throw new ResourceNotFoundException("Id informado não foi encontrado: " + id);
		} catch (DataIntegrityViolationException erro) {
			throw new DataBaseException("Violação de integridade de banco de dados!");
		}
	}

}
