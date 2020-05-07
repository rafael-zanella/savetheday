package com.zanella.savetheday.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zanella.savetheday.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CASOS")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataPublicacao;
    private Double valorArrecadado;
    private Double valorMeta;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private ONG ong;
}
