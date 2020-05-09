package com.zanella.savetheday.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.Endereco;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ONGDto {

    @NotBlank
    private String nome;
    private LocalDate dataFundacao;

    @org.hibernate.validator.constraints.br.CNPJ
    private String CNPJ;
    private String telefone;

    @Email
    private String email;

    @JsonIgnore
    @Length(min = 8, max = 16)
    private String senha;
    private Endereco endereco;

    @JsonIgnore
    private List<Caso> casos = new ArrayList<>();

    public ONGDto(String nome, LocalDate dataFundacao, String CNPJ, String telefone, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.CNPJ = CNPJ;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }
}
