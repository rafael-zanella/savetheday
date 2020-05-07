package com.zanella.savetheday.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "ONG")
public class ONG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @OneToOne(mappedBy = "ong", cascade = CascadeType.ALL)
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Caso> casos = new ArrayList<>();

    public ONG(Integer id, String nome, LocalDate dataFundacao, String CNPJ, String telefone, String email, String senha, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.CNPJ = CNPJ;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

}
