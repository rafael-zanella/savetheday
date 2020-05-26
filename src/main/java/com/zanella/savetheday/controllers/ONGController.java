package com.zanella.savetheday.controllers;

import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.dto.ONGResponseDto;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.services.ONGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ong")
public class ONGController {

    @Autowired
    private ONGService service;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ONGResponseDto> find(@PathVariable Integer id) {
        ONG obj = service.findById(id);
        return new ResponseEntity<>(new ONGResponseDto(obj), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ONGResponseDto>> findAll() {
        List<ONG> list = service.findAll();
        List<ONGResponseDto> ongDtoList = list.stream().map(obj -> new ONGResponseDto(obj)).collect(Collectors.toList());
        return new ResponseEntity<>(ongDtoList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ONGResponseDto> add(@Valid @RequestBody ONGDto ongDto) {
        ONGResponseDto responseDto = new ONGResponseDto(service.add(ongDto));
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ONGResponseDto> update(@PathVariable Integer id, @RequestBody ONGDto dto) {
        ONGResponseDto responseDto = new ONGResponseDto(service.update(id, dto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/picture")
    public ResponseEntity<ONGResponseDto> updatePicture(@PathVariable Integer id, @RequestParam(name = "file")MultipartFile file) {
        ONGResponseDto responseDto = new ONGResponseDto(service.updatePicture(id, file));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
