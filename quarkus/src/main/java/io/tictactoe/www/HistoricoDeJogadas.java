package io.tictactoe.www;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.PartidaResultado;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.result.PartidaJogada;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/historico")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class HistoricoDeJogadas {
    @Inject
    EntityManager em;
    @Inject
    SecurityIdentity identity;

    @GET
    @Transactional
    public ResponseController<List<PartidaResultado>> getHistoricoDePartidas(Usuario u) {
        return new ResponseController<List<PartidaResultado>>(() -> {
            identity.checkPermission(Authenticated)
            Query q = em.createQuery("select p from Partida p where Partida.jogadorA.id = :id or Partida.jogadorB.id = :id");
            q.setParameter("id", u.getId());
            return q.getResultStream()
                .map((e) -> new PartidaJogada(u, (Partida)e))
                .collect();
        });

    }
}
