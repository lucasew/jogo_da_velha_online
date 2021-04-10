package io.tictactoe.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany(
            cascade = CascadeType.DETACH,
            orphanRemoval = true
    )
    private List<Partida> partidas = new ArrayList<>();

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotBlank(message="Usuários tem que ter um nome")
    @Pattern(regexp = "[a-zA-Z_-]*")
    private String nome;

    @NotBlank(message="Senha não pode estar vazia")
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

    public Usuario(long id, List<Partida> partidas, @NotBlank(message = "Usuários tem que ter um nome") @Pattern(regexp = "[a-zA-Z_-]*") String nome, @NotBlank(message = "Senha não pode estar vazia") String senha) {
        this.id = id;
        this.partidas = partidas;
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
