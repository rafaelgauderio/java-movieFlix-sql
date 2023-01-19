package com.rafaeldeluca.movifliex.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.rafaeldeluca.movieflix.entities.Genre;

public class GenreDTO implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O campo nome do gênero é de preenchimento obrigatório")
	@Size(min=3, max=60, message = "O nome do gênero tem que ter entre 3 e 60 caracteres")
	private String name;
	
	public GenreDTO () {
		
	}

	public GenreDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public GenreDTO(Genre entity) {
		this.id= entity.getId();
		this.name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	

}
