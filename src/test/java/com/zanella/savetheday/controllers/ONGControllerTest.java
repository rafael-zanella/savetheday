package com.zanella.savetheday.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zanella.savetheday.Util;
import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.services.ONGService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    void setUp() { }

    @Test
    void returnAllONGs() throws Exception {
        ONG ong1 = new ONG(1, "ONGONG", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        ONG ong2 = new ONG(2, "ONGONG 2", LocalDate.of(1997,5,20), "02499010000149", "5533331146", "email@email.com", "123456789",null);
        when(this.service.findAll()).thenReturn(Arrays.asList(ong1, ong2));
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

    @Test
    void saveNewONG() throws Exception {
        ONG ong1 = new ONG(1, "ong 3", LocalDate.of(2018, 5, 13), "02499010000149", "5133331146", "email@email.com", "123456789",null);
        ONGDto dto = new ONGDto("ong 3", LocalDate.of(2018, 5, 13), "02499010000149", "5133331146", "email@email.com", "123456789");
        String data = Util.ObjectToJsonStr(dto);

        when(this.service.add(ArgumentMatchers.any(ONGDto.class))).thenReturn(ong1);

        mockMvc.perform(post("/ong/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.senha").doesNotExist());
    }

}