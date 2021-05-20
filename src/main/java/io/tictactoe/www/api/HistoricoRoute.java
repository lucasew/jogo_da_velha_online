package io.tictactoe.www.api;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.state.GameState;
import io.tictactoe.controller.domain.state.GameStateSingleton;
import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.PartidaResultado;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.UserNotFoundException;
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
public class HistoricoRoute {
    @Inject
    SecurityIdentity identity;

    @Inject
    GameState g;

    @GET
    @RolesAllowed("user")
    public ResponseController<List<PartidaJogada>> getHistoricoDePartidas() throws UserNotFoundException {
        Usuario u = g.usuarioController.getUsuarioByUsername(identity.getPrincipal().getName());
        return new ResponseController<>(() -> g.historicoController.getPartidasJogadas(u));
    }
}
