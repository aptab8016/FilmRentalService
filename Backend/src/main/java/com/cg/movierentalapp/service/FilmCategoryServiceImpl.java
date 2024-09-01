package com.cg.movierentalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.enitites.Category;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.FilmActor;
import com.cg.movierentalapp.enitites.FilmActorId;
import com.cg.movierentalapp.enitites.FilmCategory;
import com.cg.movierentalapp.enitites.FilmCategoryId;
import com.cg.movierentalapp.exception.CustomException;
import com.cg.movierentalapp.repositories.FilmCategoryRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FilmCategoryServiceImpl implements FilmCategoryService {

	private final FilmCategoryRepository filmCategoryRepository;

	@Autowired
	public FilmCategoryServiceImpl(FilmCategoryRepository filmCategoryRepository) {
		this.filmCategoryRepository = filmCategoryRepository;
	}

	// Find all Films of specified {category}
	@Override
	public List<Film> getFilmsByCategory(String category)throws CustomException {
		List<FilmCategory> filmCategories = filmCategoryRepository.findByCategoryName(category);
		List<Film> list = filmCategories.stream().map(FilmCategory::getFilm).toList();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("fIlmsCategory are not found.. ");
		}
	}

	///////// *******************REMAINING****************************////////

	@Override
	public void assignCategoryToFilm(Short filmId, Category category) {
		Film film = new Film();
		film.setFilmId(filmId);

//        FilmActorId filmActorId = new FilmActorId();
//        filmActorId.setFilmId(filmId);
//        filmActorId.setActorId(actor.getActorId());

		FilmCategoryId filmCategoryId = new FilmCategoryId();
		filmCategoryId.setFilmId(filmId);
		filmCategoryId.setCategoryId(category.getCategoryId());

//        FilmActor filmActor = new FilmActor();
//        filmActor.setId(filmActorId);
//        filmActor.setFilm(film);
//        filmActor.setActor(actor);
//        filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

		FilmCategory filmCategory = new FilmCategory();
		filmCategory.setId(filmCategoryId);
		filmCategory.setFilm(film);
		filmCategory.setCategory(category);
		filmCategory.setLastUpdate(new Timestamp(System.currentTimeMillis()));

//        filmActorRepository.save(filmActor);
		filmCategoryRepository.save(filmCategory);
	}

//    *****for custom exception**********

//    @Override
//    public void assignCategoryToFilm(Short filmId, Category category) {
//        Optional<Film> optionalFilm = filmRepository.findById(filmId);
//        
//        if (optionalFilm.isPresent()) {
//            Film film = optionalFilm.get();
//
//            FilmCategory existingFilmCategory = filmCategoryRepository.findByFilmAndCategory(film, category);
//
//            if (existingFilmCategory == null) {
//                FilmCategoryId filmCategoryId = new FilmCategoryId();
//                filmCategoryId.setFilmId(filmId);
//                filmCategoryId.setCategoryId(category.getCategoryId());
//
//                FilmCategory filmCategory = new FilmCategory();
//                filmCategory.setId(filmCategoryId);
//                filmCategory.setFilm(film);
//                filmCategory.setCategory(category);
//                filmCategory.setLastUpdate(new Timestamp(System.currentTimeMillis()));
//
//                filmCategoryRepository.save(filmCategory);
//            } else {
//                throw new CustomException("Film category already exists for the given film and category.");
//            }
//        } else {
//            throw new CustomException("Film not found for ID: " + filmId);
//        }
//    }

}