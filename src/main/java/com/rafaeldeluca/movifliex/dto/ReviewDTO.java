package com.rafaeldeluca.movifliex.dto;

import java.io.Serializable;

import com.rafaeldeluca.movieflix.entities.Review;

public class ReviewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private MovieDTO movie;
	private UserDTO user;

	public ReviewDTO() {

	}

	public ReviewDTO(Long id, String text, MovieDTO movie, UserDTO user) {

		this.id = id;
		this.text = text;
		this.movie = movie;
		this.user = user;
	}

	public ReviewDTO(Review entity) {
		this.id = entity.getId();
		this.text= entity.getText();
		this.movie = new MovieDTO(entity.getMovie());
		this.user = new UserDTO(entity.getUser());
	}

}
