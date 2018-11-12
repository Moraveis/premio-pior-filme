package com.premiopiorfilme.premiopiorfilme.service;

import com.premiopiorfilme.premiopiorfilme.model.Movie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

/**
 *
 * @author joao
 */
@Component
public class MovieService {

    private static List<Movie> movies = new LinkedList<>();
    private static AtomicInteger counter = new AtomicInteger();

    static {
        try {
            InputStream in = new FileInputStream("src/main/resources/static/movielist.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }

                String[] row = line.split(";");

                movies.add(getEntity(counter.incrementAndGet(), row));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Movie getEntity(Integer id, String[] row) {
        Integer year = null;
        try {
            year = Integer.parseInt(row[0]);
        } catch (Exception e) {
        }

        String title = row[1];
        List<String> lstStudioes = Arrays.asList(row[2].split(",\\s*"));
        List<String> lstProducers = Arrays.asList(row[3].split(",\\s*"));
        boolean winner = false;
        try {
            if (row[4].equalsIgnoreCase("yes")) {
                winner = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            winner = false;
        }

        Movie movie = new Movie(id, year, title, lstStudioes, lstProducers, winner);

        return movie;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
