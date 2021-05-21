package io.tictactoe.www.api;

import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.db.UsuarioController;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/cadastro")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CadastroRoute {
    @Inject
    UsuarioController usuarioController;

    @GET
    @Transactional
    @PermitAll
    public Response cadastrar(@QueryParam("usuario") String usuario, @QueryParam("senha") String senha) {
        return new ResponseController<>(() -> usuarioController.createUsuario(usuario, senha).getId()).call();
    }
}
