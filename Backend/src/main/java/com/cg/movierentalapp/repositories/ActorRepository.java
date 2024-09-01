package com.cg.movierentalapp.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movierentalapp.enitites.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Short> {

	List<Actor> findByLastName(String lastName);

	List<Actor> findByFirstName(String firstName);

	@Query("SELECT a FROM Actor a ORDER BY SIZE(a.films) DESC LIMIT 10")
	List<Actor> findTopTenActorsByFilmCount();

}
