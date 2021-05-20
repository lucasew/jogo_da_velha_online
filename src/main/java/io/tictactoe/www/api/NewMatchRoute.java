package io.tictactoe.www.api;

import io.quarkus.security.identity.SecurityIdentity;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.db.MatchController;
import io.tictactoe.controller.domain.state.GameState;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.UserNotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/historico")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class NewMatchRoute {
    @Inject
    SecurityIdentity identity;

    @Inject
    GameState g;

    @Inject
    MatchController matchController;

    @GET
    @RolesAllowed("user")
    public ResponseController<String> iniciarPartida() {
        return new ResponseController<>(() -> {
            Usuario u = g.usuarioController.getUsuarioByUsername(identity.getPrincipal().getName());
            return matchController.newMatch(u);
        });
    }
}
