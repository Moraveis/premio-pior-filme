package com.premiopiorfilme.premiopiorfilme.model;

import java.util.List;

/**
 *
 * @author joao
 */
public class Movie {
    private Integer id;
    private Integer year;
    private String title;
    private List<String> studios;
    private List<String> producers;
    private Boolean winner;

    public Movie(Integer id, Integer year, String title, List<String> studios, List<String> producers, Boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public Movie(){
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    public List<String> getProducers() {
        return producers;
    }

    public void setProducers(List<String> producers) {
        this.producers = producers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", year=" + year + ", title=" + title + ", studios=" + studios + ", producers=" + producers + ", winner=" + winner + '}';
    }
    
}
