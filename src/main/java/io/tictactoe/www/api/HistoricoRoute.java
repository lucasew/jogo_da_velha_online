package io.tictactoe.www.api;

import io.quarkus.security.identity.SecurityIdentity;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.db.HistoricoController;
import io.tictactoe.controller.db.UsuarioController;
import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.UserNotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response getHistoricoDePartidas() {
        return new ResponseController<>(() -> {
            Usuario u = uc.getUsuarioByUsername(identity.getPrincipal().getName());
            return hc.getPartidasJogadas(u);
        }).call();
    }
}
