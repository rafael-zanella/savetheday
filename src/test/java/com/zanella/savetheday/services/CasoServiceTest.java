package com.zanella.savetheday.services;

import com.zanella.savetheday.dto.CasoDto;
import com.zanella.savetheday.dto.ONGResponseDto;
import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.entities.enums.Status;
import com.zanella.savetheday.repositories.CasoRepository;
import com.zanella.savetheday.repositories.ONGRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CasoServiceTest {

    @Autowired
    private CasoService service;

    @Autowired
    private ONGRepository ongRepository;

    @Autowired
    private CasoRepository repository;

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void add() {
        // Cria uma ong no banco
        ONG ong = new ONG(null, "ONGONG", LocalDate.of(1997,05,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ongRepository.save(ong);

        Integer ongId = ong.getId();

        // Cria um caso e seta a ong criada
        CasoDto casoDto = new CasoDto("titulo", "desc", LocalDateTime.of(LocalDate.of(2020,03, 20), LocalTime.of(12,45)), 50.0, 200.0, Status.ABERTO, ong);
        Caso savedCaso = service.add(casoDto);
        Integer casoId = savedCaso.getId();

        ong = ongRepository.findById(ongId).orElse(null);

        assert ong != null;
        assertEquals(1, ong.getCasos().size());
        assertEquals(casoId, ong.getCasos().get(0).getId());
        assertEquals(ongId, savedCaso.getOng().getId());


    }

    @Test
    void fromDto() {
    }
}