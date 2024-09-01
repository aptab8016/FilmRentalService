package com.cg.movierentalapp.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.movierentalapp.enitites.FilmCategory;
import com.cg.movierentalapp.enitites.FilmCategoryId;

import java.util.List;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {
	//Find all Films of specified {category}
    List<FilmCategory> findByCategoryName(String category);
    
    
}
