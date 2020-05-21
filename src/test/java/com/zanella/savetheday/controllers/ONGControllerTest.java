package com.zanella.savetheday.controllers;

import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.services.ONGService;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ONGController.class)
class ONGControllerTest {

    @MockBean
    private ONGService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ONG ong1 = new ONG(1, "ONGONG", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong2 = new ONG(2, "ONGONG 2", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        when(this.service.findAll()).thenReturn(Arrays.asList(ong1, ong2));
        when(this.service.findById(1)).thenReturn(ong1);
    }

    @Test
    void returnAllONGs() throws Exception {
        mockMvc.perform(get("/ong/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[1].id").value("2"));
    }

    @Test
    void returnEmptyListWhenNoONGsRegistered() throws Exception {
        when(this.service.findAll()).thenReturn(Arrays.asList());
        mockMvc.perform(get("/ong/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(jsonPath("$").isEmpty());
    }

}