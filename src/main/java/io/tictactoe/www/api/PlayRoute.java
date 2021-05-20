package io.tictactoe.www.api;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.MatchController;
import io.tictactoe.controller.domain.board.Board;
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
    MatchController mc;

    @GET
    public Response<Board> jogar(
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
