package io.tictactoe.model.db;

import org.graalvm.compiler.lir.CompositeValue;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "tb_partida")
public class Partida implements Serializable  {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Usuario jogadorA;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Usuario jogadorB;

    @Column
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
