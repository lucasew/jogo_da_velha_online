package io.tictactoe.www;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.PartidaResultado;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.result.PartidaJogada;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
    @RolesAllowed("user")
    public ResponseController<Object[]> getHistoricoDePartidas() {
        System.out.println(identity.getPrincipal().getName());
        return new ResponseController<Object[]>(() -> {
            Query uq = em.createQuery("select u from Usuario u where u.nome = :nome");
            uq.setParameter("nome", identity.getPrincipal().getName());
            Usuario u = (Usuario) uq.getResultList().get(0);
            List<PartidaJogada> res = new ArrayList<>();
            Query q = em.createQuery("select p from Partida p where Partida.jogadorA.id = :id or Partida.jogadorB.id = :id");
            q.setParameter("id", u.getId());
            q.getResultStream().forEach((e) -> {
                Partida p = (Partida) e;
                res.add(new PartidaJogada(u, p));
            });
            return res.toArray();
        });

    }
}
