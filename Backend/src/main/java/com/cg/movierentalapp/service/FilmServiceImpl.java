
package com.cg.movierentalapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Category;

import com.cg.movierentalapp.enitites.Film;

import com.cg.movierentalapp.enitites.FilmCategory;

import com.cg.movierentalapp.enitites.FilmCategoryId;

import com.cg.movierentalapp.enitites.Language;

import com.cg.movierentalapp.exception.CustomException;

import com.cg.movierentalapp.repositories.CategoryRepository;

import com.cg.movierentalapp.repositories.FilmCategoryRepository;

import com.cg.movierentalapp.repositories.FilmRepository;

import com.cg.movierentalapp.repositories.LanguageRepository;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;

import jakarta.persistence.Query;

import jakarta.persistence.TypedQuery;

import jakarta.transaction.Transactional;

import java.sql.Timestamp;

import java.time.Instant;

import java.util.List;

import java.util.Optional;

import java.util.Set;

@Service

@Transactional

public class FilmServiceImpl implements FilmService {

	private final FilmRepository filmRepository;

	private final LanguageRepository languageRepository;

	private final CategoryRepository categoryRepository;

	private final FilmCategoryRepository filmCategoryRepository;

	@Autowired

	public FilmServiceImpl(FilmRepository filmRepository, LanguageRepository languageRepository,
			CategoryRepository categoryRepository, FilmCategoryRepository filmCategoryRepository) {

		this.filmRepository = filmRepository;

		this.languageRepository = languageRepository;

		this.categoryRepository = categoryRepository;

		this.filmCategoryRepository = filmCategoryRepository;

	}

	@Override

	public List<Film> getAllFilms() throws CustomException {

		List<Film> list = filmRepository.findAll();

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("fIlms are not found.. ");

		}

	}

	@Override

	public List<Film> searchFilmsByTitle(String title)throws CustomException {

		List<Film> list = filmRepository.findByTitleContaining(title);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("films by title are not found.. ");

		}

	}

	@Override

	public List<Film> searchFilmsByReleaseYear(int year)throws CustomException {

		List<Film> list = filmRepository.findByReleaseYear(year);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("fIlms by release year are not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration)throws CustomException {

		List<Film> list = filmRepository.findFilmsByRentalDurationGreaterThan(rentalDuration);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By Rental Duration Greater Than rental duration are not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByRentalRateGreaterThan(double rentalRate) throws CustomException{

		List<Film> list = filmRepository.findFilmsByRentalRateGreaterThan(rentalRate);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By Rental rate Greater Than rental rate are not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByLengthGreaterThan(int length)throws CustomException {

		List<Film> list = filmRepository.findFilmsByLengthGreaterThan(length);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By length Greater Than length are not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByRentalDurationLessThan(int rentalDuration)throws CustomException {

		List<Film> list = filmRepository.findFilmsByRentalDurationLessThan(rentalDuration);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By Rental Duration less Than rental duration are not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByRentalRateLessThan(double rentalRate)throws CustomException {

		List<Film> list = filmRepository.findFilmsByRentalRateLessThan(rentalRate);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By Rental rate less Than rental rate are not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByLengthLessThan(int length) throws CustomException{

		List<Film> list = filmRepository.findFilmsByLengthLessThan(length);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By length less Than length are not found.. ");

		}

	}

	@PersistenceContext

	private EntityManager entityManager;

	@Override

	public List<Film> getFilmsReleasedBetweenYears(int fromYear, int toYear)throws CustomException {

		Query query = entityManager
				.createQuery("SELECT f FROM Film f WHERE f.releaseYear BETWEEN :fromYear AND :toYear");

		query.setParameter("fromYear", fromYear);

		query.setParameter("toYear", toYear);

		List list = query.getResultList();

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films Released Between Years are not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByRatingLessThan(String rating)throws CustomException {

		TypedQuery<Film> query = entityManager.createQuery(

				"SELECT f FROM Film f WHERE ASCII(f.rating) < ASCII(:rating)", Film.class);

		query.setParameter("rating", rating);

		List<Film> list = query.getResultList();

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By Rating Less Than are rating not found.. ");

		}

	}

	@Override

	public List<Film> findFilmsByRatingGreaterThan(String rating)throws CustomException {

		TypedQuery<Film> query = entityManager.createQuery(

				"SELECT f FROM Film f WHERE ASCII(f.rating) > ASCII(:rating)", Film.class);

		query.setParameter("rating", rating);

		List<Film> list = query.getResultList();

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By Rating greater Than rating are not found.. ");

		}

	}

	@Override

	public List<Film> getFilmsByLanguage(String language)throws CustomException {

		List<Film> list = filmRepository.findByLanguageName(language);

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Films By language are not found.. ");

		}

	}

//********************************************************************************************************* 

	@Override

	public Film createFilm(Film film) {

		return filmRepository.save(film);

	}

	// Update Title of a Film

	@Override

	public void updateFilmTitle(short id, String newTitle) throws CustomException{
		if (id <= 0) {
			throw new CustomException("Invalid film ID: " + id);
		}

		if (newTitle == null || newTitle.isEmpty()) {
			throw new CustomException("Invalid film title: " + newTitle);
		}

		filmRepository.updateFilmTitleById(id, newTitle);

	}

	// Update Release Year of a Film

	@Override

	public void updateFilmReleaseYear(Short id, Integer newReleaseYear)throws CustomException {
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new CustomException("No film found with the ID: " + id));

		film.setReleaseYear(newReleaseYear);
		filmRepository.save(film);

	}

	// Update Rental Duration of a Film

	@Override

	public void updateFilmRentalDuration(Short id, Integer newRentalDuration)throws CustomException {

		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new CustomException("No film found with the ID: " + id));

		film.setRentalDuration(newRentalDuration);
		filmRepository.save(film);
	}

	// Update Rental Rate of a Film

	@Override

	public void updateFilmRentalRate(Short id, Double newRentalRate) throws CustomException{

		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new CustomException("No film found with the ID: " + id));

		film.setRentalRate(newRentalRate);
		filmRepository.save(film);

	}

	// Update Rating of a Film

	@Override

	public void updateFilmRating(Short id, String newRating)throws CustomException {

		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new CustomException("No film found with the ID: " + id));

		film.setRating(newRating);
		filmRepository.save(film);

	}

	// Update Language of a Film

	@Override

	public void updateFilmLanguage(Short filmId, String languageName)throws CustomException {

		Film film = filmRepository.findById(filmId)

				.orElseThrow(() -> new IllegalArgumentException("Film not found with id: " + filmId));

		Language language = languageRepository.findByName(languageName);

		if (language == null) {

			throw new IllegalArgumentException("Language not found with name: " + languageName);

		}

		film.setLanguage(language);

		filmRepository.save(film);

	}

	// count film by year

	@Override

	public List<Object[]> countFilmsByYear()throws CustomException {

		List<Object[]> list = filmRepository.countFilmsByYear();

		if (!list.isEmpty()) {

			return list;

		} else {

			throw new CustomException("Count films by year  are not found.. ");

		}

	}

	@Override

	public Film getFilmById(Short filmId) {

		return filmRepository.getById(filmId);

	}
	
	@Override
    public List<Film> getFilmByRating(String rating) {
        return filmRepository.findFilmsByRating(rating);
    }

}
