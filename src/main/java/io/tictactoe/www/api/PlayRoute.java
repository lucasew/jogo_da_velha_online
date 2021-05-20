package io.tictactoe.www.api;

import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.MatchController;
import io.tictactoe.controller.domain.board.Board;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/play")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class PlayRoute {
    @Inject
    MatchController mc;

    @GET
    public Response jogar(
            @QueryParam("front_id") String front,
            @QueryParam("jogada") int jogada
    ) {
        return new ResponseController<> (() -> {
            mc.playMatch(front, jogada);
            Board board = mc.getPlayerFrontend(front).getBoard();
            return board;
        }).call();
    }
}
