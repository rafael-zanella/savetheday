package com.zanella.savetheday.repositories;

import com.zanella.savetheday.entities.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasoRepository extends JpaRepository<Caso, Integer> {

    List<Caso> findAllByOng_Endereco_CidadeContaining(String nome);
}
