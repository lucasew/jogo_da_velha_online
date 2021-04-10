package io.tictactoe.www;

import io.tictactoe.controller.ResponseController;
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
public class Cadastro {
    @Inject
    EntityManager em; // Maracutaia do quarkus

    @GET // TODO: mudar pra POST depois, deixei assim pra poder usar no browser
    @Transactional
    @PermitAll
    public Response cadastrar(@QueryParam("usuario") String usuario, @QueryParam("senha") String senha) {
        return new ResponseController(() -> {
            Usuario u = new Usuario(usuario, senha);
            em.persist(u);
            return new Long(u.getId());
        }).call();
    }
}
