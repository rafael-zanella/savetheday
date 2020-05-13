package com.zanella.savetheday.controllers;

import com.zanella.savetheday.dto.EnderecoDto;
import com.zanella.savetheday.dto.EnderecoResponseDto;
import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.dto.ONGResponseDto;
import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<EnderecoResponseDto> find(@PathVariable Integer id) {
        Endereco obj = service.findById(id);
        return new ResponseEntity<>(new EnderecoResponseDto(obj), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EnderecoResponseDto>> findAll() {
        List<Endereco> list = service.findAll();
        List<EnderecoResponseDto> enderecoDtoList = list.stream().map(obj -> new EnderecoResponseDto(obj)).collect(Collectors.toList());
        return new ResponseEntity<>(enderecoDtoList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EnderecoResponseDto> add(@Valid @RequestBody EnderecoDto EnderecoDto) {
        EnderecoResponseDto responseDto = new EnderecoResponseDto(service.add(EnderecoDto));
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EnderecoResponseDto> update(@PathVariable Integer id, @RequestBody EnderecoDto dto) {
        EnderecoResponseDto responseDto = new EnderecoResponseDto(service.update(id, dto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
