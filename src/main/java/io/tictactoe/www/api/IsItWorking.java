package io.tictactoe.www.api;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.tictactoe.controller.ResponseController;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/is-it-working")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class IsItWorking {
    @GET
    public Response check() {
        return Response.ok(new Result()).build();
    }
}

@RegisterForReflection
class Result {
    public boolean result = true;
}
