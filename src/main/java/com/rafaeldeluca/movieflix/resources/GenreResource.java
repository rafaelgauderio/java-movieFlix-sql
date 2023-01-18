package com.rafaeldeluca.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaeldeluca.movieflix.services.GenreService;
import com.rafaeldeluca.movifliex.dto.GenreDTO;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

	@Autowired
	private GenreService service;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll() { // RequestParam é um tipo de dado Opcional

		List<GenreDTO> listaDTO = service.findAll();
		return ResponseEntity.ok().body(listaDTO);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> findById(@PathVariable Long id) { // PathVariable é um tipo de dado obrigatório
		GenreDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
