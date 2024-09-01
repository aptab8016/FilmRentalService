package com.cg.movierentalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.FilmActor;
import com.cg.movierentalapp.enitites.FilmActorId;
import com.cg.movierentalapp.exception.CustomException;
import com.cg.movierentalapp.repositories.ActorRepository;
import com.cg.movierentalapp.repositories.FilmActorRepository;
import com.cg.movierentalapp.repositories.FilmRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmActorServiceImpl implements FilmActorService {

	private final FilmActorRepository filmActorRepository;
	@Autowired
	private ActorRepository actorRepo;
	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	public FilmActorServiceImpl(FilmActorRepository filmActorRepository) {
		this.filmActorRepository = filmActorRepository;
	}

	// Find all Actors of a Film by Film id
	@Override
	public List<Actor> findAllActorsByFilmId(Short filmId) throws CustomException {
		List<FilmActor> filmActors = filmActorRepository.findAllByIdFilmId(filmId);
		List<Actor> list = filmActors.stream().map(FilmActor::getActor).collect(Collectors.toList());
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Find all actors bt filmId are not found.. ");
		}
	}

	@Override
	public List<Film> getFilmsByActorId(Short actorId) throws CustomException {

		List<FilmActor> filmActors = filmActorRepository.findByActorId(actorId);
		List<Film> list = filmActors.stream().map(FilmActor::getFilm).collect(Collectors.toList());
//        return filmActorRepository.findByActorId(actorId);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Films by actorId are not found.. ");
		}
	}

	// Assign Actor to a Film
//    @Override
//    public void assignActorToFilm(Short filmId, Actor actor) {
//        Film film = new Film();
//        film.setFilmId(filmId);
//
//        FilmActorId filmActorId = new FilmActorId();
//        filmActorId.setFilmId(filmId);
//        filmActorId.setActorId(actor.getActorId());
//
//        FilmActor filmActor = new FilmActor();
//        filmActor.setId(filmActorId);
//        filmActor.setFilm(film);
//        filmActor.setActor(actor);
//        filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
//
//        filmActorRepository.save(filmActor);
//    }

	@Override
	public void assignActorToFilm(Short filmId, Actor actor) throws CustomException {
		Optional<Film> optionalFilm = filmRepository.findById(filmId);

		if (optionalFilm.isPresent()) {
			Film film = optionalFilm.get();

			FilmActorId filmActorId = new FilmActorId();
			filmActorId.setFilmId(filmId);
			filmActorId.setActorId(actor.getActorId());

			FilmActor filmActor = new FilmActor();
			filmActor.setId(filmActorId);
			filmActor.setFilm(film);
			filmActor.setActor(actor);
			filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

			filmActorRepository.save(filmActor);
		} else {
			throw new CustomException("Film not found for ID: " + filmId);
		}
	}

	//// ************************Remaining***********************************/////

	// Assign Film to a Actor

	@Override
	public void assignFilmToActor(Short actorId, Film film) throws CustomException {
		Optional<Actor> findById = actorRepo.findById(actorId);
		if (findById.isPresent()) {
			Actor actor = findById.get();
			FilmActorId filmActorId = new FilmActorId();
			filmActorId.setActorId(actorId);
			filmActorId.setFilmId(film.getFilmId());

			FilmActor filmActor = new FilmActor();
			filmActor.setId(filmActorId);
			filmActor.setActor(actor);
			filmActor.setFilm(film);
			filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

			filmActorRepository.save(filmActor);
		} else {
			throw new CustomException("Actor not found for ID: " + actorId);
		}
	}

//        FilmActor filmActor = new FilmActor();
//        filmActor.setId(new FilmActorId(actorId, film.getFilmId()));
//        filmActor.setActor(actor);
//        filmActor.setFilm(film);
//
//        filmActorRepository.save(filmActor);   

}