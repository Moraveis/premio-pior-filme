package com.premiopiorfilme.premiopiorfilme.controller;

import com.premiopiorfilme.premiopiorfilme.model.entities.Movie;
import com.premiopiorfilme.premiopiorfilme.model.services.MovieService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PremiorPiorFilmeController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<Object> findAllMovies() {
        List<Movie> movies = movieService.findAll();

        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/movies/winners")
    public ResponseEntity<Object> findAllWinner() {
        List<Movie> movies = movieService.findWinners();

        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/movies/winners/{year}")
    public ResponseEntity<Object> findWinnerByYear(@PathVariable String year) {
        List<Movie> movies = movieService.findWinnerByYear(year);

        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/years")
    public ResponseEntity<Object> findYearWithMoreThanOneWinner() {
        return ResponseEntity.ok().body(movieService.findYearWithOverOneWinner());
    }

    @GetMapping("/studios")
    public ResponseEntity<Object> findStudiosSortedByNumberWins() {
        return ResponseEntity.ok().body(movieService.findStudiosSortedByNumberWins());
    }

    @GetMapping("/producer")
    public ResponseEntity<Object> findProducerWithMinAndMaxIntervalbetweenWins() {
        return ResponseEntity.ok().body(movieService.findProducerWithMinAndMaxIntervalbetweenWins());
    }

}
