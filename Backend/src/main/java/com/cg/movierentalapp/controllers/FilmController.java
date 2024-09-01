package com.cg.movierentalapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.enitites.Category;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.repositories.FilmRepository;
import com.cg.movierentalapp.service.CategoryService;
import com.cg.movierentalapp.service.FilmActorService;
import com.cg.movierentalapp.service.FilmCategoryService;
import com.cg.movierentalapp.service.FilmCategoryServiceImpl;
import com.cg.movierentalapp.service.FilmService;
import com.cg.movierentalapp.service.FilmServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class FilmController {

	@Autowired
	FilmService filmService;
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	FilmActorService filmActorService;
	@Autowired
	FilmCategoryService filmCategoryService;
	@Autowired
	FilmServiceImpl filmServiceImpl;
    @Autowired
    CategoryService categoryService;
	

	@PostMapping("/admin/films/post")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addFilm(@Valid @RequestBody Film film) {
		Film createdFilm = filmService.createFilm(film);
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
	}

	@GetMapping("/public/films")
	public ResponseEntity<List<Film>> getAllFilms() {
		List<Film> films = filmService.getAllFilms();
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/public/films/title/{title}")
	public ResponseEntity<List<Film>> searchFilmsByTitle(@PathVariable String title) {
		List<Film> films = filmService.searchFilmsByTitle(title);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/public/films/year/{year}")
	public ResponseEntity<List<Film>> searchFilmsByReleaseYear(@PathVariable int year) {
		List<Film> films = filmService.searchFilmsByReleaseYear(year);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}

	@GetMapping("/public/films/duration/gt/{rd}")
	public List<Film> findFilmsByRentalDurationGreaterThan(@PathVariable("rd") int rentalDuration) {
		return filmService.findFilmsByRentalDurationGreaterThan(rentalDuration);
	}

	@GetMapping("/public/films/rate/gt/{rate}")
	public List<Film> findFilmsByRentalRateGreaterThan(@PathVariable("rate") double rentalRate) {
		return filmService.findFilmsByRentalRateGreaterThan(rentalRate);
	}

	@GetMapping("/public/films/length/gt/{length}")
	public List<Film> findFilmsByLengthGreaterThan(@PathVariable("length") int length) {
		return filmService.findFilmsByLengthGreaterThan(length);
	}

	@GetMapping("/public/films/duration/lt/{rentalDuration}")
	public List<Film> findFilmsByRentalDurationLessThan(@PathVariable("rentalDuration") int rentalDuration) {
		return filmService.findFilmsByRentalDurationLessThan(rentalDuration);
	}

	@GetMapping("/public/films/rate/lt/{rentalRate}")
	public List<Film> findFilmsByRentalRateLessThan(@PathVariable("rentalRate") double rentalRate) {
		return filmService.findFilmsByRentalRateLessThan(rentalRate);
	}

	@GetMapping("/public/films/length/lt/{length}")
	public List<Film> findFilmsByLengthLessThan(@PathVariable("length") int length) {
		return filmService.findFilmsByLengthLessThan(length);
	}

	@GetMapping("/public/films/betweenyear/{from}/{to}")
	public List<Film> getFilmsReleasedBetweenYears(@PathVariable int from, @PathVariable int to) {
		return filmService.getFilmsReleasedBetweenYears(from, to);
	}

	@GetMapping("/public/films/rating/lt/{rating}")
	public List<Film> findFilmsByRatingLessThan(@PathVariable("rating") String rating) {
		return filmService.findFilmsByRatingLessThan(rating);
	}

	@GetMapping("/public/films/rating/gt/{rating}")
	public List<Film> getFilmsByRatingGreaterThan(@PathVariable String rating) {
		return filmService.findFilmsByRatingGreaterThan(rating);
	}

	@GetMapping("/public/films/language/{lang}")
	public List<Film> getFilmsByLanguage(@PathVariable("lang") String language) {
		return filmService.getFilmsByLanguage(language);
	}

	// count film by year
	@GetMapping("/public/films/countbyyear")
	public ResponseEntity<List<Object[]>> countFilmsByYear() {
		List<Object[]> filmsByYear = filmService.countFilmsByYear();
		return ResponseEntity.ok(filmsByYear);
	}

	// Find all Actors of a Film by Film id
	@GetMapping("/public/films/{id}/actors")
	public List<Actor> findAllActorsByFilmId(@PathVariable("id") Short filmId) {
		return filmActorService.findAllActorsByFilmId(filmId);
	}

	// Find all Films of specified {category}
	@GetMapping("/public/films/category/{category}")
	public List<Film> getFilmsByCategory(@PathVariable("category") String category) {
		return filmCategoryService.getFilmsByCategory(category);
	}

	// Update Title of a Film
	@PutMapping("/admin/films/update/title/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateFilmTitle(@Valid @PathVariable("id") short id,
			@RequestBody Map<String, String> requestBody) {
		String newTitle = requestBody.get("newTitle");
		filmService.updateFilmTitle(id, newTitle);
		return ResponseEntity.status(HttpStatus.OK).body("Film title updated successfully");
	}

	// Update Release Year of a Film
	@PutMapping("/admin/films/update/releaseyear/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateFilmReleaseYear(@Valid @PathVariable("id") Short id,
			@RequestBody Map<String, Integer> requestBody) {
		Integer newReleaseYear = requestBody.get("newReleaseYear");
		filmService.updateFilmReleaseYear(id, newReleaseYear);
		return ResponseEntity.status(HttpStatus.OK).body("Film release year updated successfully");
	}

	// Update Rental Duration of a Film
	@PutMapping("/admin/films/update/rentalduration/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateFilmRentalDuration(@PathVariable("id") Short id,
			@RequestBody Map<String, Integer> requestBody) {
		Integer newRentalDuration = requestBody.get("newRentalDuration");
		filmService.updateFilmRentalDuration(id, newRentalDuration);
		return ResponseEntity.status(HttpStatus.OK).body("Film rental duration updated successfully");
	}

	// Update Rental Rate of a Film
	@PutMapping("/admin/films/update/rentalrate/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateFilmRentalRate(@PathVariable("id") Short id,
			@RequestBody Map<String, Double> requestBody) {
		Double newRentalRate = requestBody.get("newRentalRate");
		filmService.updateFilmRentalRate(id, newRentalRate);
		return ResponseEntity.status(HttpStatus.OK).body("Film rental rate updated successfully");
	}

	// Update Rating of a Film
	@PutMapping("/customer/films/update/rating/{id}")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public ResponseEntity<String> updateFilmRating(@PathVariable("id") Short id,
			@RequestBody Map<String, String> requestBody) {
		String newRating = requestBody.get("newRating");
		filmService.updateFilmRating(id, newRating);
		return ResponseEntity.status(HttpStatus.OK).body("Film rating updated successfully");
	}

	// Update Language of a Film
	@PutMapping("/admin/films/update/language/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateFilmLanguage(@PathVariable("id") Short id,
			@RequestBody Map<String, String> requestBody) {
		String newLanguage = requestBody.get("newLanguage");
		filmService.updateFilmLanguage(id, newLanguage);
		return ResponseEntity.status(HttpStatus.OK).body("Film language updated successfully");
	}

	// Assign Actor to a Film
	@PutMapping("/admin/films/{id}/actor")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void assignActorToFilm(@PathVariable("id") Short filmId, @RequestBody Actor actor) {
		filmActorService.assignActorToFilm(filmId, actor);
	}

	// Assign category to a Film
	@PutMapping("/admin/films/update/category/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> assignCategoryToFilm(@PathVariable("id") short filmId,
			@RequestBody Category category) {
		filmCategoryService.assignCategoryToFilm(filmId, category);
		return ResponseEntity.ok("Category successfully assigned to the film!");
	}
	
	//get all categories
    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/public/films/rating/{rating}")
    public List<Film> filmByRating(@PathVariable String rating){
        return filmService.getFilmByRating(rating);
    }

}
