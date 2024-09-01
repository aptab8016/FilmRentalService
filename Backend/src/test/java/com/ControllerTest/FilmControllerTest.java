package com.ControllerTest;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
import com.cg.movierentalapp.service.*;
import com.cg.movierentalapp.controllers.*;
import com.cg.movierentalapp.enitites.*;

 

 

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(classes = FilmControllerTest.TestConfiguration.class)
class FilmControllerTest {

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @Test
    void testAddFilm() {
        Film film = new Film();
        film.setTitle("Example Film");
        film.setReleaseYear(2022);
        when(filmService.createFilm(any(Film.class))).thenReturn(film);

        ResponseEntity<String> response = filmController.addFilm(film);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Record Created Successfully", response.getBody());

        verify(filmService, times(1)).createFilm(any(Film.class));
    }

    @Test
    void testSearchFilmsByTitle() {
        Film film = new Film();
        film.setTitle("Example Film");
        film.setReleaseYear(2022);
        List<Film> films = Collections.singletonList(film);
        when(filmService.searchFilmsByTitle("Example Film")).thenReturn(films);

        ResponseEntity<List<Film>> response = filmController.searchFilmsByTitle("Example Film");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());

        verify(filmService, times(1)).searchFilmsByTitle("Example Film");
    }

    @Test
    void testUpdateFilmRentalRate() {
        Short filmId = 1;
        Double newRentalRate = 9.99;
        Film film = new Film();

        doNothing().when(filmService).updateFilmRentalRate(filmId, newRentalRate);

        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("newRentalRate", newRentalRate);

        ResponseEntity<String> response = filmController.updateFilmRentalRate(filmId, requestBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Film rental rate updated successfully", response.getBody());

        verify(filmService, times(1)).updateFilmRentalRate(filmId, newRentalRate);
    }

    @Test
    void testUpdateFilmRating() {
        Short filmId = 1;
        String newRating = "PG";
        Film film = new Film();

        doNothing().when(filmService).updateFilmRating(filmId, newRating);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("newRating", newRating);

        ResponseEntity<String> response = filmController.updateFilmRating(filmId, requestBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Film rating updated successfully", response.getBody());

        verify(filmService, times(1)).updateFilmRating(filmId, newRating);
    }

    @Test
    void testUpdateFilmLanguage() {
        Short filmId = 1;
        String newLanguage = "English";
        Film film = new Film();

        doNothing().when(filmService).updateFilmLanguage(filmId, newLanguage);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("newLanguage", newLanguage);

        ResponseEntity<String> response = filmController.updateFilmLanguage(filmId, requestBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Film language updated successfully", response.getBody());

        verify(filmService, times(1)).updateFilmLanguage(filmId, newLanguage);
    }


    @Configuration

        static class TestConfiguration {


        }
}


