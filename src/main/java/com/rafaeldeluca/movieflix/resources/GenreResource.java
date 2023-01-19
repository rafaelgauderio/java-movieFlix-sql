package com.rafaeldeluca.movieflix.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PutMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> update(@PathVariable Long id, @Valid @RequestBody GenreDTO genreDTO) {
		genreDTO = service.update(id, genreDTO);
		;
		return ResponseEntity.ok().body(genreDTO);
	}

	@PreAuthorize("hasAnyRole('MEMBER')")
	@PostMapping
	public ResponseEntity<GenreDTO> insert(@Valid @RequestBody GenreDTO genreDTO) {
		genreDTO = service.insert(genreDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genreDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(genreDTO);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
