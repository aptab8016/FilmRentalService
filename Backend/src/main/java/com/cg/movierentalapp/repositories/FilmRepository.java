package com.cg.movierentalapp.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.enitites.Category;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.Language;

public interface FilmRepository extends JpaRepository<Film, Short> {
    // Add custom query methods if needed
	
	//find by title
    List<Film> findByTitleContaining(String title);
    
    
    //find by release year
    List<Film> findByReleaseYear(int year);

    //Search Films where Rental Duration is greater 
    List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration);
    
    //Search Films where Rental Rate is greater 
    List<Film> findFilmsByRentalRateGreaterThan(double rentalRate);
    
    //Search Films where Length is greater
    List<Film> findFilmsByLengthGreaterThan(int length);
    
    //Search Films where Rental Duration is lower 
    List<Film> findFilmsByRentalDurationLessThan(int rentalDuration);
    
    //Search Films where Rental Rate is low
    List<Film> findFilmsByRentalRateLessThan(double rentalRate);
    
    //Search Films where Length is lower 
    List<Film> findFilmsByLengthLessThan(int length);
    
    //Search Films which are Released between {from} and {to}
    //is in service interface
    
    //Search Films where Rating is lower 
    List<Film> findFilmsByRatingLessThan(String rating);
    
    //Search Films where Rating is greater 
    List<Film> findFilmsByRatingGreaterThan(String rating);
    
	//Find by language
    List<Film> findByLanguageName(String language);
	
	
	//Update Title of a Film
    @Modifying
    @Query("UPDATE Film f SET f.title = :newTitle WHERE f.id = :id")
    void updateFilmTitleById(short id, String newTitle);
    
    //Update Language of a Film
    @Modifying
    @Query("UPDATE Film f SET f.language = :language WHERE f.id = :filmId")
    void updateFilmLanguage(@Param("filmId") Short filmId, @Param("language") Language language);
    
    //Count film by year
    @Query("SELECT f.releaseYear, COUNT(f) FROM Film f GROUP BY f.releaseYear")
    List<Object[]> countFilmsByYear();


	List<Film> findFilmsByRating(String rating);
    
    //Find all Films of specified {category}
    
    //get all categories
    
    
    
    
}
