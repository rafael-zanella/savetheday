package com.zanella.savetheday.repositories;

import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.entities.enums.Status;
import com.zanella.savetheday.repositories.ONGRepository;
import org.apache.tomcat.jni.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ONGRepositoryTest {

    @Autowired
    private ONGRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CasoRepository casoRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        enderecoRepository.deleteAll();
        casoRepository.deleteAll();
    }

    @Test
    public void shouldPersistData() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        repository.save(ong);
        ONG savedONG = repository.findById(ong.getId()).orElse(null);
        assertNotNull(savedONG);
    }

    @Test
    public void shouldUpdateData() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        repository.save(ong);
        Integer id = ong.getId();
        ong.setNome("NOVO NOME DA ONG");
        repository.save(ong);
        assertEquals(id, ong.getId());
        assertEquals("NOVO NOME DA ONG", ong.getNome());
    }

    @Test
    public void ShouldDeleteData() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        repository.save(ong);

        assertEquals(1, repository.count());
        repository.deleteById(ong.getId());
        assertEquals(0, repository.count());
    }

    @Test
    public void shouldSaveEnderecoReference() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        Endereco endereco = new Endereco(null, "RS", "CIDADE", "RUA", "BAIRRO", "COMPLEMENTO", "NUMERO", "CEP", ong);
        ong.setEndereco(endereco);
        repository.save(ong);
        //enderecoRepository.save(endereco);

        ONG savedOng = repository.findById(ong.getId()).orElse(null);

        assert savedOng != null;
        assertEquals(endereco.getId(), savedOng.getEndereco().getId());
    }


    @Test
    public void updateEnderecoReference() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        Endereco endereco = new Endereco(null, "RS", "CIDADE", "RUA", "BAIRRO", "COMPLEMENTO", "NUMERO", "CEP", ong);
        ong.setEndereco(endereco);
        repository.save(ong);
        enderecoRepository.save(endereco);

        endereco.setCidade("Cidade Atualizada");
        enderecoRepository.save(endereco);

        ONG savedOng = repository.findById(ong.getId()).orElse(null);

        assert savedOng != null;
        assertEquals("Cidade Atualizada", savedOng.getEndereco().getCidade());
    }


    @Test
    public void updateCasoReference() {
        ONG ong = new ONG(null, "ONGONG", new Date("20/05/1997 13:30"), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        Caso caso = new Caso(null,"Tratamento","Cachorro atropelado precisa de tratamento", new Date("20/03/2020 12:45"),
                15.50, 200.00, Status.ABERTO, ong);
        ong.getCasos().addAll(Arrays.asList(caso));

        repository.save(ong);
        casoRepository.save(caso);

        caso.setTitulo("Titulo atualizado");
        casoRepository.save(caso);

        ONG savedOng = repository.findById(ong.getId()).orElse(null);

        assert savedOng != null;
        assertEquals("Titulo atualizado", savedOng.getCasos().get(0).getTitulo());


    }
}