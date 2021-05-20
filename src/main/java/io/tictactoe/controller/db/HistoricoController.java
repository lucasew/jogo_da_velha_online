package io.tictactoe.controller.db;

import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.utils.AppLogger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class HistoricoController {
    @Inject
    EntityManager em;

    @Inject
    AppLogger appLogger;

    public List<Partida> getPartidasJogadas(Usuario u) {
        Query q = em.createQuery("select p from Partida p where Partida.jogadorA.id = :id or Partida.jogadorB.id = :id");
        q.setParameter("id", u.getId());
        return q.getResultList();
    }

    public void putPartida(Partida pu) {
        em.persist(pu);
        appLogger.info(String.format("Salva partida %s (%s vs %s) Resultado: %s",
                pu.getId(),
                pu.getJogadorA().getNome(),
                pu.getJogadorB().getNome(),
                pu.getResultado().toString()
        ));
    }
}
