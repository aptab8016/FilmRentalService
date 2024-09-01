package com.cg.movierentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.movierentalapp.enitites.Language;

public interface LanguageRepository extends JpaRepository<Language, Byte>{

	Language findByName(String languageName);

}
