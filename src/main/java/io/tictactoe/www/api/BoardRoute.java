package io.tictactoe.www.api;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.tictactoe.controller.ResponseController;
import io.tictactoe.controller.domain.MatchController;
import io.tictactoe.controller.domain.board.Board;
import io.tictactoe.controller.domain.board.BoardPlayer;
import io.tictactoe.controller.domain.board.BoardResult;
import io.tictactoe.controller.domain.board.PlayerFrontend;

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
            PlayerFrontend f = mc.getPlayerFrontend(front);
            Board board = f.getBoard();
//            System.out.println(board);
            String adversary = mc.getAdversaryName(front);
            return new BoardRouteResult(board, adversary, f.getBoardResult());
        }).call();
    }
}

@RegisterForReflection
class BoardRouteResult {
    public BoardPlayer[] board;
    public String adversary;
    public BoardResult result;

    public BoardRouteResult(Board board, String adversary, BoardResult result) {
        this.board = board.getPosicoes();
        this.adversary = adversary;
        this.result = result;
    }
}

