package com.cg.movierentalapp.enitites;

import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "film")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Short filmId;

	@NotBlank(message = "Title is required")
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description")
	private String description;

	@Min(value = 1900, message = "Release year must be at least 1900")
	@Column(name = "release_year")
	private int releaseYear;

	@NotNull(message = "Language is required")
	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false)
	@JsonIgnoreProperties("films")
//    @JsonIgnore
	private Language language;

	@ManyToOne
	@JoinColumn(name = "original_language_id")
	private Language originalLanguage;

	@Min(value = 1, message = "Rental duration must be at least 1")
	@Column(name = "rental_duration", nullable = false)
	private int rentalDuration;

	@DecimalMin(value = "0.0", message = "Rental rate must be a positive number")
	@Column(name = "rental_rate", nullable = false)
	private Double rentalRate;

	@PositiveOrZero(message = "Length must be a positive number or zero")
	@Column(name = "length")
	private int length;

	@DecimalMin(value = "0.0", message = "Replacement cost must be a positive number")
	@Column(name = "replacement_cost", nullable = false)
	private Double replacementCost;

	@NotBlank(message = "Rating is required")
	@Column(name = "rating", nullable = false)
	private String rating;

	@Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes') DEFAULT NULL")
	private String specialFeatures;

	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private Set<Actor> actors;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	public Film() {
		super();

	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Film(Short filmId, String title, String description, int releaseYear, Language language,
			Language originalLanguage, int rentalDuration, Double rentalRate, int length, Double replacementCost,
			String rating, String specialFeatures, Timestamp lastUpdate, Set<Actor> actors) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.originalLanguage = originalLanguage;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.lastUpdate = lastUpdate;
		this.actors = actors;
	}

	public Short getFilmId() {
		return filmId;
	}

	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", language=" + language + ", originalLanguage=" + originalLanguage
				+ ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate + ", length=" + length
				+ ", replacementCost=" + replacementCost + ", rating=" + rating + ", specialFeatures=" + specialFeatures
				+ ", lastUpdate=" + lastUpdate + "]";
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	// Getters and setters
}