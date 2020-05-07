package com.zanella.savetheday.services;

import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ONGServiceTest {

    @Autowired
    private ONGService service;

    @Test
    void successfulAddNewONG() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        service.add(ong);
        assertNotNull(ong.getId());
    }

    @Test
    void findByIdThrowsObjectNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.findById(9999999));
    }

    @Test
    void findByIdShouldReturnONG() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        service.add(ong);
        ONG savedOng = service.findById(ong.getId());
        assertNotNull(savedOng);
        assertEquals(new Date("20/05/1997 13:30"), savedOng.getDataFundacao());
    }
}