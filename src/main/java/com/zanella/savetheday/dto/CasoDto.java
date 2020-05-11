package com.zanella.savetheday.dto;

import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CasoDto {

    private String titulo;
    private String descricao;
    private LocalDateTime dataPublicacao;
    private Double valorArrecadado;
    private Double valorMeta;

    @Enumerated(EnumType.STRING)
    private Status status;

    private ONG ong;

    public CasoDto(Caso caso) {
        this.titulo = caso.getTitulo();
        this.descricao = caso.getDescricao();
        this.dataPublicacao = caso.getDataPublicacao();
        this.valorArrecadado = caso.getValorArrecadado();
        this.valorMeta = caso.getValorMeta();
        this.status = caso.getStatus();
        this.ong = caso.getOng();
    }
}
