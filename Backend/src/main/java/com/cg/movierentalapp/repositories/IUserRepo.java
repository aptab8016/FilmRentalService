package com.cg.movierentalapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.movierentalapp.enitites.User;


public interface IUserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}
