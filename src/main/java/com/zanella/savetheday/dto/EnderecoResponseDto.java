package com.zanella.savetheday.dto;

import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDto {

    private Integer id;
    private String UF;
    private String cidade;
    private String rua;
    private String bairro;
    private String complemento;
    private String numero;
    private String CEP;
    private Integer ongId;

    public EnderecoResponseDto(Endereco obj) {
        this.id = obj.getId();
        this.UF = obj.getUF();
        this.cidade = obj.getCidade();
        this.rua = obj.getRua();
        this.bairro = obj.getBairro();
        this.complemento = obj.getComplemento();
        this.numero = obj.getNumero();
        this.CEP = obj.getCEP();
        this.ongId = obj.getOng().getId();
    }

}
