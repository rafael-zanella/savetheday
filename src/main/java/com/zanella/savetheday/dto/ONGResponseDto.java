package com.zanella.savetheday.dto;

import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

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
