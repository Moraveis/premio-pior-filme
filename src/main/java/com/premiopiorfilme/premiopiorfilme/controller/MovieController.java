package com.premiopiorfilme.premiopiorfilme.controller;

import com.premiopiorfilme.premiopiorfilme.model.ApiError;
import com.premiopiorfilme.premiopiorfilme.model.Movie;
import com.premiopiorfilme.premiopiorfilme.model.Producer;
import com.premiopiorfilme.premiopiorfilme.model.Studio;
import com.premiopiorfilme.premiopiorfilme.model.Year;
import com.premiopiorfilme.premiopiorfilme.service.MovieService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joao
 */
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<Object> getMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovies());
    }

    @GetMapping("/winners")
    public ResponseEntity<Object> getWinners() {
        List<Movie> winners = new LinkedList<>();
        for (Movie m : movieService.getMovies()) {
            if (m.isWinner()) {
                winners.add(m);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(winners);
    }

    @GetMapping("/winners/{year}")
    public ResponseEntity<Object> getWinnersByYear(@PathVariable Integer year) {
        List<Movie> winnerYear = new LinkedList<>();
        for (Movie m : movieService.getMovies()) {
            if (m.getYear() == year && m.isWinner()) {
                winnerYear.add(m);
            }
        }

        if (winnerYear.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(winnerYear);
    }

    @GetMapping("/years")
    public ResponseEntity<Object> getYearsOverTwoWinners() {
        Year years = new Year();
        HashMap<Integer, Integer> map = new LinkedHashMap<>();

        for (Movie m : movieService.getMovies()) {
            Integer winnerCount = map.get(m.getYear());
            if (winnerCount == null) {
                map.put(m.getYear(), 0);
            }
            if (winnerCount == null && m.isWinner()) {
                map.put(m.getYear(), 1);
            } else if (m.isWinner()) {
                map.put(m.getYear(), ++winnerCount);
            }
        }

        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() > 1) {
                years.getYears().add(new Year.Item(item.getKey(), item.getValue()));
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(years);
    }

    @GetMapping("/studios")
    public ResponseEntity<Object> getStudiosOrderedCountVictory() {
        Studio studios = new Studio();

        Map<String, Integer> map = new LinkedHashMap<>();

        movieService.getMovies().stream()
                .forEach(x -> {
                    boolean win = x.isWinner();
                    for (String s : x.getStudios()) {
                        Integer count = map.get(s);

                        if (count == null && !win) {
                            map.put(s, 0);
                        } else if (count == null && win) {
                            map.put(s, 1);
                        } else if (win) {
                            map.put(s, ++count);
                        }
                    }
                });

        Map<String, Integer> sorted = map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        sorted.entrySet().stream()
                .forEach(i -> {
                    studios.getStudios().add(new Studio.Item(i.getKey(), i.getValue()));
                });

        return ResponseEntity.status(HttpStatus.OK).body(studios);
    }

    @GetMapping("/producers")
    public ResponseEntity<Object> getProducersStatistics() {
        HashMap<String, List<Integer>> mapProducerYears = new HashMap<>();
        Producer producer = new Producer();

        movieService.getMovies().stream()
                .filter(m -> m.isWinner())
                .forEach(a -> {
                    for (String b : a.getProducers()) {
                        List<Integer> aux = mapProducerYears.get(b);
                        if (aux == null) {
                            aux = new LinkedList<>();
                            aux.add(a.getYear());
                            mapProducerYears.put(b, aux);
                        } else {
                            aux.add(a.getYear());
                            mapProducerYears.put(b, aux);
                        }
                    }
                });

        List<Producer.Item> list = new ArrayList<>();
        mapProducerYears.entrySet().stream()
                .filter(m -> m.getValue().size() > 1)
                .forEach(i -> {
                    
                    Producer.Item minItem = null;
                    List<Integer> aux = i.getValue();
                    Integer min = null, previous = null, next = null, diff = null;

                    for (int a = 0; a < (aux.size() - 1); a++) {
                        previous = aux.get(a);
                        next = aux.get(a + 1);
                        diff = next - previous;
                        if (min == null) {
                            min = diff;
                        } else if (min > diff) {
                            min = diff;
                        }
                    }
                    
                    minItem = new Producer.Item(i.getKey(), diff, previous, next);
                    list.add(minItem);
                });

        Producer.Item minByInterval = list
                .stream()
                .min(Comparator.comparing(Producer.Item::getInterval))
                .orElseThrow(NoSuchElementException::new);
        
        list.clear();
        mapProducerYears.entrySet().stream()
                .filter(m -> m.getValue().size() > 1)
                .forEach(i -> {
                    
                    Producer.Item minItem = null;
                    List<Integer> aux = i.getValue();
                    Integer max = null, previous = null, next = null, diff = null;

                    for (int a = 0; a < (aux.size() - 1); a++) {
                        previous = aux.get(a);
                        next = aux.get(a + 1);
                        diff = next - previous;
                        if (max == null) {
                            max = diff;
                        } else if (max < diff) {
                            max = diff;
                        }
                    }
                    
                    minItem = new Producer.Item(i.getKey(), diff, previous, next);
                    list.add(minItem);
                });

        Producer.Item maxByInterval = list
                .stream()
                .max(Comparator.comparing(Producer.Item::getInterval))
                .orElseThrow(NoSuchElementException::new);

        producer.setMin(minByInterval);
        producer.setMax(maxByInterval);
        return ResponseEntity.status(HttpStatus.OK).body(producer);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Object> removeMovie(@PathVariable Integer id) {
        Movie movie = movieService.getMovies().stream()
                .filter(m -> m.getId() == id)
                .collect(Collectors.toList()).get(0);

        if (!movie.isWinner()) {
            movieService.getMovies().remove(movie);
        } else {
            ApiError error = new ApiError();
            error.setStatus(HttpStatus.FORBIDDEN);
            error.setMessage("Não é possivel deletar filmes vencedores");
            error.setPath("/movies/".concat(String.valueOf(id)));

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }

        return ResponseEntity.ok().build();
    }
}
