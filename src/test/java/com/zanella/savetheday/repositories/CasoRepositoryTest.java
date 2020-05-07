package com.zanella.savetheday.repositories;

import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.entities.enums.Status;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CasoRepositoryTest {

    @Autowired
    private CasoRepository repository;

    @Autowired
    private ONGRepository ongRepository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        ongRepository.deleteAll();
    }

    @Test
    public void shouldPersistData() {
        Caso caso = new Caso(
                null,
                "Tratamento",
                "Cachorro atropelado precisa de tratamento",
                LocalDateTime.of(LocalDate.of(2020,03, 20), LocalTime.of(12,45)),
                15.50, 200.00,
                Status.ABERTO,
                null);

        repository.save(caso);

        assertNotNull(caso.getId());
        assertEquals("Tratamento", caso.getTitulo());
        assertEquals("Cachorro atropelado precisa de tratamento", caso.getDescricao());
        assertEquals(LocalDateTime.of(LocalDate.of(2020,03, 20), LocalTime.of(12,45)), caso.getDataPublicacao());
        assertEquals(15.50, caso.getValorArrecadado());
        assertEquals(200.00, caso.getValorMeta());
        assertEquals(Status.ABERTO, caso.getStatus());
        assertNull(caso.getOng());
        assertEquals(1, repository.count());
    }

    @Test
    public void shouldSaveONGReference() {
        ONG ong = new ONG(null, "ONG_NOME", LocalDate.of(1997,05,20), "02499010000149", "5133333333", "email@email.com", "123456789", null);
        Caso caso = new Caso(null,"Tratamento","Cachorro atropelado precisa de tratamento", LocalDateTime.of(LocalDate.of(2020,03, 20), LocalTime.of(12,45)),
                15.50, 200.00, Status.ABERTO, ong);
        ong.getCasos().addAll(Arrays.asList(caso));

        ongRepository.save(ong);
        repository.save(caso);

        assertEquals(1, repository.count());
        assertEquals(caso.getId(), ong.getCasos().get(0).getId());
        assertEquals(1, ong.getCasos().size());
    }

    @Test
    public void shouldUpdateData() {
        Caso caso = new Caso(null,"Tratamento","Cachorro atropelado precisa de tratamento", LocalDateTime.of(LocalDate.of(2020,03, 20), LocalTime.of(12,45)),
                15.50, 200.00, Status.ABERTO, null);

        repository.save(caso);
        Integer id = caso.getId();

        caso.setTitulo("Titulo Atualizado!");
        repository.save(caso);

        assertEquals(id, caso.getId());
        assertEquals(1, repository.count());
    }

    @Test
    public void shouldDeleteData() {
        Caso caso = new Caso(null,"Tratamento","Cachorro atropelado precisa de tratamento", LocalDateTime.of(LocalDate.of(2020,03, 20), LocalTime.of(12,45)),
                15.50, 200.00, Status.ABERTO, null);

        repository.save(caso);
        assertEquals(1, repository.count());
        repository.deleteById(caso.getId());
        assertEquals(0, repository.count());
    }

}