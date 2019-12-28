package com.premiopiorfilme.premiopiorfilme.model.repositories;

import com.premiopiorfilme.premiopiorfilme.model.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Optional<Producer> findByProducer(String producer);
}
