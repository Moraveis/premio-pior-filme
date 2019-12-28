package com.premiopiorfilme.premiopiorfilme.model.repositories;

import com.premiopiorfilme.premiopiorfilme.model.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    Optional<Studio> findByName(String studio);
}
