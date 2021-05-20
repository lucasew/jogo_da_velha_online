package io.tictactoe.www.api;

import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.board.Board;
import io.tictactoe.controller.domain.state.GameState;
import io.tictactoe.model.Response;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/play")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class PlayRoute {
    @Inject
    GameState g;

    @GET
    public Response<Board> jogar(
            @QueryParam("front_id") String front,
            @QueryParam("jogada") int jogada
    ) {
        return new ResponseController<> (() -> {
            g.playMatch(front, jogada);
            return g.getPlayerFrontend(front).getBoard();
        }).call();
    }
}
