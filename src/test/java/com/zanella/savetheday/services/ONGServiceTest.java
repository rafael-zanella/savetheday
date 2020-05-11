package com.zanella.savetheday.services;

import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.h2.constraint.Constraint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ONGServiceTest {

    @Autowired
    private ONGService service;

    @Test
    void successfulAddNewONG() {
        ONGDto ongDto = new ONGDto("ONGONG", LocalDate.of(1997, 5, 20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG saved = service.add(ongDto);
        assertNotNull(saved.getId());
    }

    @Test
    void failAddNewONGWithInvalidCNPJ() {
        ONGDto ongDto = new ONGDto("ONGONG", LocalDate.of(1997, 5, 20), "0249901", "5533331146", "email@email.com", "123456789",null);
        assertThrows(ConstraintViolationException.class, () -> service.add(ongDto));
    }

    @Test
    void findByIdThrowsObjectNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.findById(9999999));
    }

    @Test
    void findByIdShouldReturnONG() {
        ONGDto ongDto = new ONGDto("ONGONG", LocalDate.of(1997, 5, 20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG savedOng = service.add(ongDto);
        savedOng = service.findById(savedOng.getId());
        assertNotNull(savedOng);
        assertEquals(LocalDate.of(1997,5,20), savedOng.getDataFundacao());
    }

    @Test
    void findAll() {
        ONGDto ongDto1 = new ONGDto("ONG 1", LocalDate.of(1997, 5, 20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONGDto ongDto2 = new ONGDto("ONG 2", LocalDate.of(1997, 5, 20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONGDto ongDto3 = new ONGDto("ONG 3", LocalDate.of(1997, 5, 20), "02499010000149", "5533331146", "email@email.com", "123456789",null);

        ONG ong1 = service.add(ongDto1);
        ONG ong2 = service.add(ongDto2);
        ONG ong3 = service.add(ongDto3);

        List<ONG> ongs = service.findAll();

        assertEquals(ong1.getId(), ongs.get(0).getId());
        assertEquals(ong2.getId(), ongs.get(1).getId());
        assertEquals(ong3.getId(), ongs.get(2).getId());

        assertEquals(3, ongs.size());
    }

    @Test
    void updateData() {
        ONGDto ong1 = new ONGDto("ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONGDto ong2 = new ONGDto("ong 2", LocalDate.of(1998,5,20), null, null, "emaildois@email.com", "321321321",null);

        ONG saved = service.add(ong1);
        Integer id = saved.getId();

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
        ONGDto ong1 = new ONGDto("ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONGDto ong2 = new ONGDto(null, LocalDate.of(1998,5,20), null, null, "emaildois@email.com", null,null);
        ONG saved = service.add(ong1);
        Integer id = saved.getId();
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
        ONGDto ong1 = new ONGDto("ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONGDto ong2 = new ONGDto();
        ong2.setSenha("7777777777");
        ONG saved = service.add(ong1);
        Integer id = saved.getId();
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