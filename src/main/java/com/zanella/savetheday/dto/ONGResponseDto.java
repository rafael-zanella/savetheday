package com.zanella.savetheday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ONGResponseDto {

    private Integer id;
    private String nome;
    private LocalDate dataFundacao;
    private String CNPJ;
    private String telefone;
    private String email;
    private Endereco endereco;

    public ONGResponseDto(ONG obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.dataFundacao = obj.getDataFundacao();
        this.CNPJ = obj.getCNPJ();
        this.telefone = obj.getTelefone();
        this.email = obj.getEmail();
        this.endereco = obj.getEndereco();
    }

}
