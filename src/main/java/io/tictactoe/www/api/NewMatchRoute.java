package io.tictactoe.www.api;

import io.quarkus.security.identity.SecurityIdentity;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.db.UsuarioController;
import io.tictactoe.controller.domain.MatchController;
import io.tictactoe.model.db.Usuario;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/new-match")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class NewMatchRoute {

    @Inject
    SecurityIdentity identity;

    @Inject
    UsuarioController uc;

    @Inject
    MatchController matchController;

    @GET
    @RolesAllowed("user")
    public Response iniciarPartida() {
        return new ResponseController<String>(() -> {
            Usuario u = uc.getUsuarioByUsername(identity.getPrincipal().getName());
            String match = matchController.getMatch(u);
            return match;
        }).call();
    }
}