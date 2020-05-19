package com.zanella.savetheday.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @NotBlank
    @org.hibernate.validator.constraints.br.CNPJ
    private String CNPJ;

    private String telefone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 8, max = 16)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @OneToOne(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ONG ong = (ONG) o;
        return id.equals(ong.id) &&
                nome.equals(ong.nome) &&
                Objects.equals(dataFundacao, ong.dataFundacao) &&
                Objects.equals(CNPJ, ong.CNPJ) &&
                Objects.equals(telefone, ong.telefone) &&
                email.equals(ong.email) &&
                senha.equals(ong.senha) &&
                Objects.equals(endereco, ong.endereco) &&
                Objects.equals(casos, ong.casos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, dataFundacao, CNPJ, telefone, email, senha, endereco, casos);
    }
}
