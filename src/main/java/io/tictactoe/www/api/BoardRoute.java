package io.tictactoe.www.api;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.MatchController;
import io.tictactoe.controller.domain.board.Board;
import io.tictactoe.controller.domain.board.BoardPlayer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/board/{front_id}")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class BoardRoute {
    @Inject
    MatchController mc;

    @GET
    public Response getBoard(
            @PathParam("front_id") String front
    ) {
        return new ResponseController<>(() -> {
            Board board = mc.getPlayerFrontend(front).getBoard();
            System.out.println(board);
            String adversary = mc.getAdversaryName(front);
            return new BoardRouteResult(board, adversary);
        }).call();
    }
}

@RegisterForReflection
class BoardRouteResult {
    public BoardPlayer[] board;
    public String adversary;

    public BoardRouteResult(Board board, String adversary) {
        this.board = board.getPosicoes();
        this.adversary = adversary;
    }
}

