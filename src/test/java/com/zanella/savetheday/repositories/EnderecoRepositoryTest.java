package com.zanella.savetheday.repositories;

import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private ONGRepository ongRepository;

    private String UF = "RS";
    private String CIDADE = "Gravataí";
    private String RUA = "Dom feliciano";
    private String BAIRRO = "Central";
    private String COMPLEMENTO = null;
    private String NUMERO = "1765";
    private String CEP = "94065000";

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        ongRepository.deleteAll();
    }

    @Test
    public void shouldPersistData() {
        Endereco endereco = new Endereco(null, UF, CIDADE, RUA, BAIRRO, COMPLEMENTO, NUMERO, CEP, null);

        Endereco savedEndereco = repository.save(endereco);

        assertNotNull(savedEndereco);
        assertNotNull(savedEndereco.getId());
        assertEquals(UF, savedEndereco.getUF());
        assertEquals(CIDADE, savedEndereco.getCidade());
        assertEquals(RUA, savedEndereco.getRua());
        assertEquals(BAIRRO, savedEndereco.getBairro());
        assertEquals(COMPLEMENTO, savedEndereco.getComplemento());
        assertEquals(NUMERO, savedEndereco.getNumero());
        assertEquals(CEP, savedEndereco.getCEP());
        assertNull(savedEndereco.getOng());

    }

    @Test
    public void shouldSaveAnONGReference() {
        ONG ong = new ONG(null, "ONG_NOME", new Date("20/05/1997 13:30"), "1234", "5133333333", "email@email.com", "123", null);
        Endereco endereco = new Endereco(null, UF, CIDADE, RUA, BAIRRO, COMPLEMENTO, NUMERO, CEP, ong);
        ong.setEndereco(endereco);

        ong = ongRepository.save(ong);
        endereco = repository.save(endereco);

        assertEquals(ong.getId(), endereco.getOng().getId());
    }

    @Test
    public void shouldDeleteData() {
        Endereco endereco = new Endereco(null, UF, CIDADE, RUA, BAIRRO, COMPLEMENTO, NUMERO, CEP, null);
        endereco = repository.save(endereco);
        assertNotNull(repository.findById(endereco.getId()).orElse(null));
        repository.deleteById(endereco.getId());
        assertNull(repository.findById(endereco.getId()).orElse(null));
    }

    @Test
    public void shouldUpdateData() {
        Endereco endereco = new Endereco(null, UF, CIDADE, RUA, BAIRRO, COMPLEMENTO, NUMERO, CEP, null);
        endereco = repository.save(endereco);

        Integer id = endereco.getId();
        endereco.setCidade("NOVA CIDADE");
        endereco = repository.save(endereco);

        assertEquals(id, endereco.getId());
        assertEquals(UF, endereco.getUF());
        assertEquals("NOVA CIDADE", endereco.getCidade());
        assertEquals(RUA, endereco.getRua());
        assertEquals(BAIRRO, endereco.getBairro());
        assertEquals(COMPLEMENTO, endereco.getComplemento());
        assertEquals(NUMERO, endereco.getNumero());
        assertEquals(CEP, endereco.getCEP());
        assertEquals(1, repository.count());
    }
}