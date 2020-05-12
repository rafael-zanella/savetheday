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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ONGDto {

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataFundacao;

    @NotBlank
    @org.hibernate.validator.constraints.br.CNPJ
    private String CNPJ;

    private String telefone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 8, max = 16)
    private String senha;

    public ONGDto(String nome, LocalDate dataFundacao, String CNPJ, String telefone, String email, String senha) {
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.CNPJ = CNPJ;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

}
