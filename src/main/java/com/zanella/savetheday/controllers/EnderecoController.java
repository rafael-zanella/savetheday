package com.zanella.savetheday.controllers;

import com.zanella.savetheday.dto.EnderecoDto;
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
    public ResponseEntity<EnderecoDto> find(@PathVariable Integer id) {
        Endereco obj = service.findById(id);
        return new ResponseEntity<>(new EnderecoDto(obj), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EnderecoDto>> findAll() {
        List<Endereco> list = service.findAll();
        List<EnderecoDto> enderecoDtoList = list.stream().map(obj -> new EnderecoDto(obj)).collect(Collectors.toList());
        return new ResponseEntity<>(enderecoDtoList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EnderecoDto> add(@Valid @RequestBody EnderecoDto EnderecoDto) {
        EnderecoDto responseDto = new EnderecoDto(service.add(EnderecoDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
