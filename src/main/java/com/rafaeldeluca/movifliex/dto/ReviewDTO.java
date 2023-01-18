package com.rafaeldeluca.movifliex.dto;

import java.io.Serializable;

import com.rafaeldeluca.movieflix.entities.Review;

public class ReviewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private MovieDTO movie;
	private Long movieId;
	private UserDTO user;

	public ReviewDTO() {

	}	

	public ReviewDTO(Long id, String text, Long movieId, UserDTO user) {
		
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.user = user;
	}



	public ReviewDTO(Review entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.movieId = entity.getMovie().getId();
		this.user = new UserDTO(entity.getUser());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}	
	

}
