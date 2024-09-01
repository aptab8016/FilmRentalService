package com.cg.movierentalapp.enitites;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class FilmCategoryId implements Serializable {

	@NotNull(message = "Film ID is required")
	@Column(name = "film_id")
	private Short filmId;

	@NotNull(message = "Category ID is required")
	@Column(name = "category_id")
	private Byte categoryId;

	public FilmCategoryId(Short filmId, Byte categoryId) {
		super();
		this.filmId = filmId;
		this.categoryId = categoryId;
	}

	public FilmCategoryId() {
		super();

	}

	public Short getFilmId() {
		return filmId;
	}

	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}

	public Byte getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Byte categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmCategoryId other = (FilmCategoryId) obj;
		return Objects.equals(categoryId, other.categoryId) && Objects.equals(filmId, other.filmId);
	}

}
