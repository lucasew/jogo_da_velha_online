package io.tictactoe.www.api;

import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.state.GameState;
import io.tictactoe.model.Response;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/cadastro")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CadastroRoute {
    @Inject
    GameState g;

    @GET // TODO: mudar pra POST depois, deixei assim pra poder usar no browser
    @Transactional
    @PermitAll
    public Response cadastrar(@QueryParam("usuario") String usuario, @QueryParam("senha") String senha) {
        return new ResponseController<>(() -> g.usuarioController.createUsuario(usuario, senha).getId()).call();
    }
}
