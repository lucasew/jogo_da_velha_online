package io.tictactoe.www.api;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/login-status")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LoginStatusRoute {
    @GET
    @RolesAllowed("user")
    public Response isLogado() { // se for false ele vai dar erro
        return Response.ok(true).build();
    }
}
