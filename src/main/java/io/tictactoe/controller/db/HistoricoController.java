package io.tictactoe.controller.db;

import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.Usuario;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class HistoricoController {
    private static final Logger LOGGER = Logger.getLogger(HistoricoController.class.getName());

    @Inject
    EntityManager em;

    public List<Partida> getPartidasJogadas(Usuario u) {
        Query q = em.createQuery("select p from Partida p where p.jogadorA.id = :id");
        q.setParameter("id", u.getId());
        return q.getResultList();
    }

    @Transactional
    public void putPartida(Partida pu) {
        em.persist(pu);
        LOGGER.info(String.format("Salva partida %s (%s vs %s) Resultado: %s",
                pu.getId(),
                pu.getJogadorA().getNome(),
                pu.getJogadorB().getNome(),
                pu.getResultado().toString()
        ));
    }
}
