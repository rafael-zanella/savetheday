package com.zanella.savetheday.services;

import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ONGServiceTest {

    @Autowired
    private ONGService service;

    @Test
    void successfulAddNewONG() {
        ONG ong = new ONG(null, "ONGONG", LocalDate.of(1997, 05, 20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        service.add(ong);
        assertNotNull(ong.getId());
    }

    @Test
    void findByIdThrowsObjectNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.findById(9999999));
    }

    @Test
    void findByIdShouldReturnONG() {
        ONG ong = new ONG(null, "ONGONG", LocalDate.of(1997,05,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        service.add(ong);
        ONG savedOng = service.findById(ong.getId());
        assertNotNull(savedOng);
        assertEquals(LocalDate.of(1997,05,20), savedOng.getDataFundacao());
    }

    @Test
    void findAll() {
        ONG ong1 = new ONG(null, "ong 1", LocalDate.of(1997,05,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong2 = new ONG(null, "ong 2", LocalDate.of(1997,05,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong3 = new ONG(null, "ong 3", LocalDate.of(1997,05,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);

        service.add(ong1);
        service.add(ong2);
        service.add(ong3);

        assertEquals(3, service.findAll().size());
    }
}