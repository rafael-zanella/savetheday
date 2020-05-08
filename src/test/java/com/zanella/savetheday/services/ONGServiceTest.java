package com.zanella.savetheday.services;

import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ONGServiceTest {

    @Autowired
    private ONGService service;

    @Test
    void successfulAddNewONG() {
        ONG ong = new ONG(null, "ONGONG", LocalDate.of(1997, 5, 20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        service.add(ong);
        assertNotNull(ong.getId());
    }

    @Test
    void findByIdThrowsObjectNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.findById(9999999));
    }

    @Test
    void findByIdShouldReturnONG() {
        ONG ong = new ONG(null, "ONGONG", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        service.add(ong);
        ONG savedOng = service.findById(ong.getId());
        assertNotNull(savedOng);
        assertEquals(LocalDate.of(1997,5,20), savedOng.getDataFundacao());
    }

    @Test
    void findAll() {
        ONG ong1 = new ONG(null, "ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong2 = new ONG(null, "ong 2", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong3 = new ONG(null, "ong 3", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);

        service.add(ong1);
        service.add(ong2);
        service.add(ong3);

        assertEquals(3, service.findAll().size());
    }

    @Test
    void updateData() {
        ONG ong1 = new ONG(null, "ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong2 = new ONG(null, "ong 2", LocalDate.of(1998,5,20), null, null, "emaildois@email.com", "321321321",null);
        service.add(ong1);
        Integer id = ong1.getId();
        service.update(id, ong2);
        ONG updated = service.findById(id);

        assertEquals(id, updated.getId());
        assertEquals("ong 2", updated.getNome());
        assertEquals(LocalDate.of(1998,05,20), updated.getDataFundacao());
        assertEquals("02499010000149", updated.getCNPJ());
        assertEquals("5533331146", updated.getTelefone());
        assertEquals("email@email.com", updated.getEmail());
        assertEquals("321321321", updated.getSenha());
        assertNull(updated.getEndereco());
    }

    @Test
    void nameAndPasswordShouldNotBeNull() {
        ONG ong1 = new ONG(null, "ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong2 = new ONG(null, null, LocalDate.of(1998,5,20), null, null, "emaildois@email.com", null,null);
        service.add(ong1);
        Integer id = ong1.getId();
        service.update(id, ong2);
        ONG updated = service.findById(id);

        assertEquals(id, updated.getId());
        assertEquals("ong 1", updated.getNome());
        assertEquals(LocalDate.of(1998,5,20), updated.getDataFundacao());
        assertEquals("02499010000149" ,updated.getCNPJ());
        assertEquals("5533331146", updated.getTelefone());
        assertEquals("email@email.com", updated.getEmail());
        assertEquals("123456789", updated.getSenha());
        assertNull(updated.getEndereco());
    }

    @Test
    void fieldsNotInformedShouldNotUpdate() {
        ONG ong1 = new ONG(null, "ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong2 = new ONG();
        ong2.setSenha("7777777777");
        service.add(ong1);
        Integer id = ong1.getId();
        service.update(id, ong2);
        ONG updated = service.findById(id);

        assertEquals(id, updated.getId());
        assertEquals("ong 1", updated.getNome());
        assertEquals(LocalDate.of(1997,5,20), updated.getDataFundacao());
        assertEquals("02499010000149", updated.getCNPJ());
        assertEquals("5533331146", updated.getTelefone());
        assertEquals("email@email.com", updated.getEmail());
        assertEquals("7777777777", updated.getSenha());
        assertNull(updated.getEndereco());
    }
}