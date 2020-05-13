package com.zanella.savetheday.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.zanella.savetheday.dto.CasoDto;
import com.zanella.savetheday.dto.CasoResponseDto;
import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.dto.ONGResponseDto;
import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.services.CasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/caso")
public class CasoController {

    @Autowired
    private CasoService service;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CasoResponseDto> find(@PathVariable Integer id) {
        Caso obj = service.findById(id);
        return new ResponseEntity<>(new CasoResponseDto(obj), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CasoResponseDto>> findAll() {
        List<Caso> list = service.findAll();
        List<CasoResponseDto> casoResponseDtoList = list.stream().map(obj -> new CasoResponseDto(obj)).collect(Collectors.toList());
        return new ResponseEntity<>(casoResponseDtoList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CasoResponseDto> add(@Valid @RequestBody CasoDto CasoDto) {
        CasoResponseDto responseDto = new CasoResponseDto(service.add(CasoDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CasoResponseDto> update(@PathVariable Integer id, @RequestBody CasoDto dto) {
        CasoResponseDto responseDto = new CasoResponseDto(service.update(id, dto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
