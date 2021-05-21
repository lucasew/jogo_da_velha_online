package io.tictactoe.controller.db;

import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.PartidaResultado;
import io.tictactoe.model.db.Usuario;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Singleton
public class HistoricoController {
    private static final Logger LOGGER = Logger.getLogger(HistoricoController.class.getName());

    @Inject
    EntityManager em;

    public List<Partida> getPartidasJogadas(Usuario u) {
        System.out.println(u);
        System.out.println(u.getId());
        ArrayList<Partida> l = new ArrayList<>();
        try {
            Query q = em.createQuery("select p from Partida p where Partida.jogadorA.id > 0 and Partida .jogadorB > 0 and Partida.jogadorA.id = :id or Partida.jogadorB.id = :id");
            q.setParameter("id", u.getId());
            q.getResultStream().forEach((item) -> {
                l.add((Partida) item);
            });
        }
        catch (NullPointerException e) {}
        finally {
            return l;
        }

    }

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
