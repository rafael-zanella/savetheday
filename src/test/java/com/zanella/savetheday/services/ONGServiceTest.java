package com.zanella.savetheday.services;

import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.entities.enums.Status;
import com.zanella.savetheday.repositories.CasoRepository;
import com.zanella.savetheday.repositories.ONGRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ONGServiceTest {

    @Mock
    private ONGRepository repositoryMock;

    @InjectMocks
    private ONGService service;

    @Test
    void add() {
        ONG ong = new ONG(1, "ONG 1", new Date("20/05/1997 13:30"), "1234", "5533331146", "email@email.com", "123",null);
        when(repositoryMock.save(ong)).thenReturn(ong);

        assertEquals(ong, service.add(ong));
    }
}