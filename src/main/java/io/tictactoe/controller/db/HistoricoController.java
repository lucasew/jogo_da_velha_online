package io.tictactoe.controller.db;

import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.result.PartidaJogada;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HistoricoController {
    @Inject
    EntityManager em;

    public List<PartidaJogada> getPartidasJogadas(Usuario u) {
        Query q = em.createQuery("select p from Partida p where Partida.jogadorA.id = :id or Partida.jogadorB.id = :id");
        q.setParameter("id", u.getId());
        List<PartidaJogada> res = new ArrayList<>();
        q.getResultStream().forEach((e) -> {
            Partida p = (Partida) e;
            res.add(new PartidaJogada(u, p));
        });
        return res;
    }

    public void putPartida(Partida pu) {
        em.persist(pu);
    }
}
