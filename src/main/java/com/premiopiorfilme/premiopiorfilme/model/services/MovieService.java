package com.premiopiorfilme.premiopiorfilme.model.services;

import com.premiopiorfilme.premiopiorfilme.model.entities.Movie;
import com.premiopiorfilme.premiopiorfilme.model.entities.Producer;
import com.premiopiorfilme.premiopiorfilme.model.entities.Studio;
import com.premiopiorfilme.premiopiorfilme.model.wrappers.ProducerWrapper;
import com.premiopiorfilme.premiopiorfilme.model.wrappers.Year;
import com.premiopiorfilme.premiopiorfilme.model.repositories.MovieRepository;
import com.premiopiorfilme.premiopiorfilme.model.repositories.ProducerRepository;
import com.premiopiorfilme.premiopiorfilme.model.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private ProducerRepository producerRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findWinners() {
        return movieRepository.findAll()
                .stream()
                .filter(Movie::getWinner)
                .collect(Collectors.toList());
    }

    public List<Movie> findWinnerByYear(String year) {
        Optional<List<Movie>> movies = movieRepository.findByYear(year);

        return movies.orElseGet(ArrayList::new);
    }

    public List<Year> findYearWithOverOneWinner() {
        List<Year> years = new ArrayList<>();

        movieRepository.findAll()
                .stream()
                .filter(Movie::getWinner)
                .collect(Collectors.groupingBy(Movie::getYear, Collectors.counting()))
                .forEach((key, value) -> years.add(new Year(String.valueOf(key), value)));

        return years.stream().filter(year -> year.getWinnerCount() > 1).collect(Collectors.toList());
    }

    public List<Studio> findStudiosSortedByNumberWins() {

        return studioRepository.findAll().stream()
                .sorted(Comparator.comparing(Studio::getWinCount).reversed())
                .collect(Collectors.toList());
    }

    //TODO: to be finished
    public ProducerWrapper findProducerWithMinAndMaxIntervalbetweenWins() {
        ProducerWrapper producerWrapper = new ProducerWrapper();
        Map<Producer, List<String>> mappingProducersWinsYears = new HashMap<>();

        movieRepository.findAll()
                .stream()
                .filter(Movie::getWinner)
                .forEach(movie -> {
                    movie.getProducers().forEach(producer -> {
                        List<String> winYears = mappingProducersWinsYears.getOrDefault(producer, new ArrayList<String>());
                        if (!winYears.contains(movie.getYear())) {
                            winYears.add(movie.getYear());
                        }

                        mappingProducersWinsYears.put(producer, winYears);
                    });
                });

         return producerWrapper;
    }

}
