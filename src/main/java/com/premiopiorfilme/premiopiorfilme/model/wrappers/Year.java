package com.premiopiorfilme.premiopiorfilme.model.wrappers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class Year implements Serializable {

    private String year;
    private Long winnerCount;

    public Year() {}

    public Year(String year, Long winnerCount) {
        this.year = year;
        this.winnerCount = winnerCount;
    }
}
