package com.premiopiorfilme.premiopiorfilme.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joao
 */
public class Studio {

    private List<Item> studios = new LinkedList<>();

    public Studio() {
    }

    public List<Item> getStudios() {
        return studios;
    }

    public void setStudios(List<Item> studios) {
        this.studios = studios;
    }
    
    public static class Item {

        private String name;
        private Integer winCount;

        public Item(String name, Integer winCount) {
            this.name = name;
            this.winCount = winCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getWinCount() {
            return winCount;
        }

        public void setWinCount(Integer winCount) {
            this.winCount = winCount;
        }
    }

}
