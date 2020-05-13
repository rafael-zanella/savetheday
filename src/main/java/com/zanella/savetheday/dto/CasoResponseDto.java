package com.zanella.savetheday.dto;

import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.ONG;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CasoResponseDto {

    private Integer id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataPublicacao;
    private Double valorArrecadado;
    private Double valorMeta;
    private ONGResponseDto ong;

    public CasoResponseDto(Caso caso) {
        this.id = caso.getId();
        this.titulo = caso.getTitulo();
        this.descricao = caso.getDescricao();
        this.dataPublicacao = caso.getDataPublicacao();
        this.valorArrecadado = caso.getValorArrecadado();
        this.valorMeta = caso.getValorMeta();
        this.ong = new ONGResponseDto(caso.getOng());
    }

}
