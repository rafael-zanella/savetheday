package com.zanella.savetheday.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ENDERECOS")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String UF;
    private String cidade;
    private String rua;
    private String bairro;
    private String complemento;
    private String numero;
    private String CEP;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ong_id")
    private ONG ong;

}
