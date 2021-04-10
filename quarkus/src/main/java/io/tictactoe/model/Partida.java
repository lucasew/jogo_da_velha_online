package io.tictactoe.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private Usuario jogadorA;
    @ManyToOne
    private Usuario jogadorB;

    @Enumerated(EnumType.STRING)
    private PartidaResultado resultado;

    public Partida(long id, Usuario jogadorA, Usuario jogadorB, PartidaResultado resultado) {
        this.id = id;
        this.jogadorA = jogadorA;
        this.jogadorB = jogadorB;
        this.resultado = resultado;
    }

    public Partida() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getJogadorA() {
        return jogadorA;
    }

    public void setJogadorA(Usuario jogadorA) {
        this.jogadorA = jogadorA;
    }

    public Usuario getJogadorB() {
        return jogadorB;
    }

    public void setJogadorB(Usuario jogadorB) {
        this.jogadorB = jogadorB;
    }

    public PartidaResultado getResultado() {
        return resultado;
    }

    public void setResultado(PartidaResultado resultado) {
        this.resultado = resultado;
    }
}
