package io.tictactoe.www.api;

import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.state.GameState;
import io.tictactoe.controller.domain.state.GameStateSingleton;
import io.tictactoe.model.Response;
import io.tictactoe.model.db.Usuario;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/cadastro")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CadastroRoute {
    @Inject
    EntityManager em; // Maracutaia do quarkus

    @Inject
    GameState g;

    @GET // TODO: mudar pra POST depois, deixei assim pra poder usar no browser
    @Transactional
    @PermitAll
    public Response cadastrar(@QueryParam("usuario") String usuario, @QueryParam("senha") String senha) {
        return new ResponseController(() -> g.usuarioController.createUsuario(usuario, senha).getId()).call();
    }
}
