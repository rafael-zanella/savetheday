package com.zanella.savetheday.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataFundacao;
    private String CNPJ;
    private String telefone;
    private String email;

    @JsonIgnore
    private String senha;

    @OneToOne(mappedBy = "ong", cascade = CascadeType.ALL)
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Caso> casos = new ArrayList<>();

    public ONG(Integer id, String nome, Date dataFundacao, String CNPJ, String telefone, String email, String senha, Endereco endereco) {
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
