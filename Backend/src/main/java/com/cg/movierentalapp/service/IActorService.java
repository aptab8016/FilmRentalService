package com.cg.movierentalapp.service;

import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.enitites.Film;

import java.util.Collection;
import java.util.List;

public interface IActorService {
	Collection<Actor> findAllActors();

    Actor addActor(Actor actor);

    Collection<Actor> findActorsByLastName(String lastName);

    Collection<Actor> findActorsByFirstName(String firstName);

    Actor updateActorLastName(Short id, String lastName);

    Actor updateActorFirstName(Short id, String firstName);
    
    List<Actor> getTopTenActorsByFilmCount();


    
}
