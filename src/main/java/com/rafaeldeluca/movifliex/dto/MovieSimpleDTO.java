package com.rafaeldeluca.movifliex.dto;

import java.io.Serializable;

import com.rafaeldeluca.movieflix.entities.Movie;

public class MovieSimpleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;

	public MovieSimpleDTO() {

	}

	public MovieSimpleDTO(Movie entity) {

		this.id = entity.getId();
		this.title = entity.getTitle();
		this.subTitle = entity.getSubTitle();
		this.year = entity.getYear();
		this.imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
