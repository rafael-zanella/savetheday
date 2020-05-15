package com.zanella.savetheday.services;

import com.zanella.savetheday.dto.EnderecoDto;
import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.repositories.EnderecoRepository;
import com.zanella.savetheday.repositories.ONGRepository;
import com.zanella.savetheday.services.exceptions.OngAddressException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnderecoServiceTest {

    @Autowired
    private EnderecoService service;

    @Autowired
    private ONGService ongService;

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private ONGRepository ongRepository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        ongRepository.deleteAll();
    }

    @Test
    void add() {
        ONGDto ongDto = new ONGDto("ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789");
        ONG ong = ongService.add(ongDto);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Porto Alegre", "Rua do comercio", "Pindorama", null, "1765", "94065000", ong);

        Endereco endereco = service.add(enderecoDto);
        ong = ongService.findById(ong.getId());

        assertEquals(ong.getId(), endereco.getOng().getId());
        assertEquals(endereco.getId(), ong.getEndereco().getId());
    }

    @Test
    void shouldNotAddEndereco() {
        ONGDto ongDto = new ONGDto("ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789");
        ONG ong = ongService.add(ongDto);
        EnderecoDto enderecoDto = new EnderecoDto("RS", "Porto Alegre", "Rua do comercio", "Pindorama", null, "1765", "94065000", ong);
        Endereco endereco = service.add(enderecoDto);

        EnderecoDto enderecoDto1 = new EnderecoDto("RS", "Porto Alegre", "Rua do comercio", "Pindorama", null, "1765", "94065000", ong);
        assertThrows(OngAddressException.class, () -> service.add(enderecoDto1));

        ong = ongService.findById(ong.getId());
        assertEquals(ong.getId(), endereco.getOng().getId());
        assertEquals(endereco.getId(), ong.getEndereco().getId());
    }

    @Test
    void updateEndereco() {
        ONGDto ongDto = new ONGDto("ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789");
        ONG ong = ongService.add(ongDto);
        EnderecoDto enderecoDto = new EnderecoDto("RS", "Porto Alegre", "Rua do comercio", "Pindorama", null, "1765", "94065000", ong);
        Endereco endereco = service.add(enderecoDto);
        EnderecoDto newEnderecoDto = new EnderecoDto("RS", "Ijuí", "Rua do comercio", "Pindorama", null, "1765", "94065000", null);

        Endereco updated = service.update(endereco.getId(), newEnderecoDto);

        ong = ongService.findById(ong.getId());
        assertEquals(ong.getId(), updated.getOng().getId());
        assertEquals(updated.getId(), ong.getEndereco().getId());
    }

    @Test
    void delete() {
        ONGDto ongDto = new ONGDto("ong 1", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789");
        ONG ong = ongService.add(ongDto);

        EnderecoDto enderecoDto = new EnderecoDto("RS", "Porto Alegre", "Rua do comercio", "Pindorama", null, "1765", "94065000", ong);

        Endereco endereco = service.add(enderecoDto);
        ong = ongService.findById(ong.getId());

        service.delete(endereco.getId());

        assertEquals(0, repository.count());


    }
}