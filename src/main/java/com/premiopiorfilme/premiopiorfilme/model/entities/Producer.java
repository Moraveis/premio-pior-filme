package com.premiopiorfilme.premiopiorfilme.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Producer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producer;

    @Column(name = "interval_between_wins")
    private Integer interval;

    private String previousWin;

    private String followingWin;
}
