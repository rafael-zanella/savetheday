package com.zanella.savetheday.dto;

import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    @NotEmpty private String UF;
    @NotEmpty private String cidade;
    @NotEmpty private String rua;
    @NotEmpty private String bairro;
    private String complemento;
    @NotEmpty private String numero;
    @NotEmpty private String CEP;
    @NotNull  private ONG ong;

    public EnderecoDto(Endereco obj) {
        this.UF = obj.getUF();
        this.cidade = obj.getCidade();
        this.rua = obj.getRua();
        this.bairro = obj.getBairro();
        this.complemento = obj.getComplemento();
        this.numero = obj.getNumero();
        this.CEP = obj.getCEP();
        this.ong = obj.getOng();
    }

}
