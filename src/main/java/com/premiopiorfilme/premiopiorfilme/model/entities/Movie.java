package com.premiopiorfilme.premiopiorfilme.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year;

    private String title;

    private Boolean winner;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Producer> producers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Studio> studios = new HashSet<>();
}
