package com.cg.movierentalapp.enitites;

import jakarta.validation.constraints.NotNull;

public class InventoryRequest {

	@NotNull(message = "Store ID is required")
	private Integer storeId;

	@NotNull(message = "Film is required")
	private Film film;

	public InventoryRequest() {
		// Default constructor (required for JSON deserialization)
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
}