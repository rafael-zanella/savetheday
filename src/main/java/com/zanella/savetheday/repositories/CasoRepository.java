package com.zanella.savetheday.repositories;

import com.zanella.savetheday.entities.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasoRepository extends JpaRepository<Caso, Integer> {
}
