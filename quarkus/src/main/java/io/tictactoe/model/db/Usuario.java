package io.tictactoe.model.db;

import io.quarkus.security.jpa.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@UserDefinition
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "username", columnNames = {"nome"})
})
//@Table(name = "tb_usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(
            cascade = CascadeType.DETACH,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Partida> partidas;

    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotBlank(message="Usuários tem que ter um nome")
    @Pattern(regexp = "[a-zA-Z_-]*")
    @Username
    private String nome;

    @Roles
    @Transient
    public String roles = "user"; // usado internamente pelo controle de acesso do quarkus

    @Column
    @NotBlank(message="Senha não pode estar vazia")
    @Password(PasswordType.CLEAR)
    private String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(@NotBlank(message = "Usuários tem que ter um nome") @Pattern(regexp = "[a-zA-Z_-]*") String nome, @NotBlank(message = "Senha não pode estar vazia") String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario() {
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }
}
