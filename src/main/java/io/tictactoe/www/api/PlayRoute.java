package io.tictactoe.www.api;

import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.MatchController;
import io.tictactoe.controller.domain.board.Board;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/play/{front_id}")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class PlayRoute {
    @Inject
    MatchController mc;

    @GET
    public Response jogar(
            @PathParam("front_id") String front,
            @QueryParam("jogada") int jogada
    ) {
        return new ResponseController<> (() -> {
            mc.playMatch(front, jogada);
            return "OK";
        }).call();
    }
}