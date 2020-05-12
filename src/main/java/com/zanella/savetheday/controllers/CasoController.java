package com.zanella.savetheday.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.zanella.savetheday.dto.CasoDto;
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
    public ResponseEntity<CasoDto> find(@PathVariable Integer id) {
        Caso obj = service.findById(id);
        return new ResponseEntity<>(new CasoDto(obj), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CasoDto>> findAll() {
        List<Caso> list = service.findAll();
        List<CasoDto> casoDtoList = list.stream().map(obj -> new CasoDto(obj)).collect(Collectors.toList());
        return new ResponseEntity<>(casoDtoList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CasoDto> add(@Valid @RequestBody CasoDto CasoDto) {
        CasoDto responseDto = new CasoDto(service.add(CasoDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
