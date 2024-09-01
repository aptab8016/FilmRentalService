package com.ServiceImplTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

 

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

 

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.cg.movierentalapp.service.*;
import com.cg.movierentalapp.controllers.*;
import com.cg.movierentalapp.enitites.*;
import com.cg.movierentalapp.exception.CustomException;
import com.cg.movierentalapp.repositories.FilmRepository;

 

 

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(classes = FilmServiceImplTest.TestConfiguration.class)
class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    @Test
    void testCreateFilm() {
        Film film = new Film();
        film.setTitle("Example Film");
        film.setReleaseYear(2022);

        when(filmRepository.save(any(Film.class))).thenReturn(film);

        Film createdFilm = filmService.createFilm(film);

        assertNotNull(createdFilm);
        assertEquals("Example Film", createdFilm.getTitle());
        assertEquals(2022, createdFilm.getReleaseYear());

        verify(filmRepository, times(1)).save(film);
    }

    @Test
    void testSearchFilmsByTitle() {
        Film film = new Film();
        film.setTitle("Example Film");
        film.setReleaseYear(2022);
        List<Film> films = Collections.singletonList(film);

        when(filmRepository.findByTitleContaining("Example Film")).thenReturn(films);

        List<Film> result = filmService.searchFilmsByTitle("Example Film");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Example Film", result.get(0).getTitle());
        assertEquals(2022, result.get(0).getReleaseYear());

        verify(filmRepository, times(1)).findByTitleContaining("Example Film");
    }

    @Test
    void testSearchFilmsByReleaseYear() {
        Film film = new Film();
        film.setTitle("Example Film");
        film.setReleaseYear(2022);
        List<Film> films = Collections.singletonList(film);

        when(filmRepository.findByReleaseYear(2022)).thenReturn(films);

        List<Film> result = filmService.searchFilmsByReleaseYear(2022);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Example Film", result.get(0).getTitle());
        assertEquals(2022, result.get(0).getReleaseYear());

        verify(filmRepository, times(1)).findByReleaseYear(2022);
    }

    @Test
    void testUpdateFilmRentalRate() {
        Short filmId = 1;
        Double newRentalRate = 9.99;
        Film film = new Film();
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        when(filmRepository.save(film)).thenReturn(film);

        assertDoesNotThrow(() -> filmService.updateFilmRentalRate(filmId, newRentalRate));

        assertEquals(newRentalRate, film.getRentalRate());
        verify(filmRepository, times(1)).findById(filmId);
        verify(filmRepository, times(1)).save(film);
    }

    @Test
    void testUpdateFilmRating() {
        Short filmId = 1;
        String newRating = "PG";
        Film film = new Film();
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        when(filmRepository.save(film)).thenReturn(film);

        assertDoesNotThrow(() -> filmService.updateFilmRating(filmId, newRating));

        assertEquals(newRating, film.getRating());
        verify(filmRepository, times(1)).findById(filmId);
        verify(filmRepository, times(1)).save(film);
    }


  
   
    
    @Configuration
    static class TestConfiguration {

    }
    }
