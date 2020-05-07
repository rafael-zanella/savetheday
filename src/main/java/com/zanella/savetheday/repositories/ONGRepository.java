package com.zanella.savetheday.repositories;

import com.zanella.savetheday.entities.ONG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ONGRepository extends JpaRepository<ONG, Integer> {
}
