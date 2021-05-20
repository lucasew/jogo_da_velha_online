package io.tictactoe.www.api;

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

@Path("/api/board")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class BoardRoute {
    @Inject
    MatchController mc;

    @GET
    public Response<BoardRouteResult> getBoard(
            @QueryParam("front_id") String front
    ) {
        return new ResponseController<>(() -> {
            Board board = mc.getPlayerFrontend(front).getBoard();
            String adversary = mc.getAdversaryName(front);
            return new BoardRouteResult(board, adversary);
        }).call();
    }
}


class BoardRouteResult {
    public Board board;
    public String adversary;

    public BoardRouteResult(Board board, String adversary) {
        this.board = board;
        this.adversary = adversary;
    }
}

