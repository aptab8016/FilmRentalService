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
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.service.FilmActorService;
import com.cg.movierentalapp.service.IActorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

	private final IActorService actorService;

	@Autowired
	public ActorController(IActorService actorService) {
		this.actorService = actorService;
	}

	@Autowired
	FilmActorService filmActorService;
	
	@PostMapping("/admin/actors/post")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addActor(@Valid @RequestBody Actor actor) {
		actorService.addActor(actor);
		return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
	}

	//--------------------get-------------------------------------------------
	
	@GetMapping("/public/actors")
	public ResponseEntity<List<Actor>> getAllActors() {
		List<Actor> actors = (List<Actor>) actorService.findAllActors();
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@GetMapping("/public/actors/toptenbyfilmcount")
	public ResponseEntity<List<Actor>> getTopTenActorsByFilmCount() {
		List<Actor> actors = (List<Actor>) actorService.getTopTenActorsByFilmCount();
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}
	
	@GetMapping("/public/actors/lastname/{ln}")
	public ResponseEntity<List<Actor>> searchActorsByLastName(@PathVariable String ln) {
		List<Actor> actors = (List<Actor>) actorService.findActorsByLastName(ln);
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@GetMapping("/public/actors/firstname/{fn}")
	public ResponseEntity<List<Actor>> searchActorsByFirstName(@PathVariable String fn) {
		List<Actor> actors = (List<Actor>) actorService.findActorsByFirstName(fn);
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}
	
	// with exception
	@GetMapping("/public/actors/{id}/films")
	public ResponseEntity<List<Film>> getFilmsByActorId(@PathVariable("id") Short actorId) {
		List<Film> films = filmActorService.getFilmsByActorId(actorId);
		return ResponseEntity.ok(films);
	}
	
	//--------------------put-------------------------------------------------

	// with exception
	@PutMapping("/admin/actors/update/lastname/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateActorLastName(@Validated @PathVariable("id") Short id,
			@RequestBody Map<String, String> requestBody) {
		String newLastName = requestBody.get("newLastName");
		actorService.updateActorLastName(id, newLastName);
		return ResponseEntity.ok("Last Name Updated Successfully");
	}

	@PutMapping("/admin/actors/update/firstname/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> updateActorFirstName(@PathVariable("id") Short id,
			@RequestBody Map<String, String> requestBody) {
		String newFirstname = requestBody.get("newFirstname");
		actorService.updateActorFirstName(id, newFirstname);
		return ResponseEntity.status(HttpStatus.OK).body("First name Updated Successfully");
	}

	@PutMapping("/admin/actors/{id}/film")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> assignFilmToActor(@PathVariable("id") Short actorId, @RequestBody Film film) {
		filmActorService.assignFilmToActor(actorId, film);
		return ResponseEntity.ok("Film successfully assigned to the actor.");
	}


}