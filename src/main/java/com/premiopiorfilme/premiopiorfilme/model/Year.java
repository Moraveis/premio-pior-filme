package com.premiopiorfilme.premiopiorfilme.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joao
 */
public class Year {
    
    private List<Item> years = new LinkedList<>();

    public Year() {
    }

    public List<Item> getYears() {
        return years;
    }

    public void setYears(List<Item> years) {
        this.years = years;
    }

    public static class Item {

        private Integer year;
        private Integer winnerCount;

        public Item(Integer year, Integer winnerCount) {
            this.year = year;
            this.winnerCount = winnerCount;
        }
        
        public Item() {}

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer getWinnerCount() {
            return winnerCount;
        }

        public void setWinnerCount(Integer winnerCount) {
            this.winnerCount = winnerCount;
        }
    }
}
