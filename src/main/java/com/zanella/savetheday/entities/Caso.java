package com.zanella.savetheday.entities;

import com.zanella.savetheday.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CASOS")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String descricao;

    @NotNull
    private LocalDateTime dataPublicacao;

    private Double valorArrecadado;

    @NotNull
    private Double valorMeta;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private ONG ong;
}
