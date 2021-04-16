package io.tictactoe.model.result;

import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.PartidaResultado;
import io.tictactoe.model.db.Usuario;

import java.io.Serializable;

public class PartidaJogada implements Serializable {
    public String adversario;
    public PartidaResultado resultado;

    PartidaResultado inverter(PartidaResultado r) {
        if (r == PartidaResultado.GANHOU) {
            return PartidaResultado.PERDEU;
        }
        if (r == PartidaResultado.PERDEU) {
            return PartidaResultado.GANHOU;
        }
        return r;
    }
    public PartidaJogada(Usuario u, Partida p) {
        if (p.getJogadorA().getId() == u.getId()) {
            this.resultado = p.getResultado();
            this.adversario = p.getJogadorB().getNome();
        } else {
            this.resultado = this.inverter(p.getResultado());
            this.adversario = p.getJogadorA().getNome();
        }
    }
}
