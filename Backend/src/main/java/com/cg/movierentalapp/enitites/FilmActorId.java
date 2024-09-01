package com.cg.movierentalapp.enitites;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class FilmActorId implements Serializable {

	@NotNull(message = "Actor ID is required")
	@Column(name = "actor_id")
	private Short actorId;

	@NotNull(message = "Film ID is required")
	@Column(name = "film_id")
	private Short filmId;

	public FilmActorId(Short actorId, Short filmId) {
		super();
		this.actorId = actorId;
		this.filmId = filmId;
	}

	public FilmActorId() {
		super();

	}

	public Short getActorId() {
		return actorId;
	}

	public void setActorId(Short actorId) {
		this.actorId = actorId;
	}

	public Short getFilmId() {
		return filmId;
	}

	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId, filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmActorId other = (FilmActorId) obj;
		return Objects.equals(actorId, other.actorId) && Objects.equals(filmId, other.filmId);
	}

	// Constructors, equals, and hashCode methods
}