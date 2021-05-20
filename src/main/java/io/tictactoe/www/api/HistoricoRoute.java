package io.tictactoe.www.api;

import io.quarkus.security.identity.SecurityIdentity;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.db.HistoricoController;
import io.tictactoe.controller.db.UsuarioController;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.UserNotFoundException;
import io.tictactoe.model.result.PartidaJogada;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Inherited;
import java.util.List;

@Path("/api/historico")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class HistoricoRoute {
    @Inject
    SecurityIdentity identity;

    @Inject
    HistoricoController hc;

    @Inject
    UsuarioController uc;

    @GET
    @RolesAllowed("user")
    public ResponseController<List<PartidaJogada>> getHistoricoDePartidas() throws UserNotFoundException {
        Usuario u = uc.getUsuarioByUsername(identity.getPrincipal().getName());
        return new ResponseController<>(() -> hc.getPartidasJogadas(u));
    }
}
