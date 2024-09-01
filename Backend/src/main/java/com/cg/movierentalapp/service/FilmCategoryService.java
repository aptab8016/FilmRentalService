package com.cg.movierentalapp.service;


import java.util.List;

import com.cg.movierentalapp.enitites.Actor;
import com.cg.movierentalapp.enitites.Category;
import com.cg.movierentalapp.enitites.Film;
import com.cg.movierentalapp.enitites.FilmCategory;
import com.cg.movierentalapp.enitites.FilmCategoryId;

public interface FilmCategoryService {
	//Find all Films of specified {category}
    List<Film> getFilmsByCategory(String category);
    
    void assignCategoryToFilm(Short filmId, Category category);
    

}
