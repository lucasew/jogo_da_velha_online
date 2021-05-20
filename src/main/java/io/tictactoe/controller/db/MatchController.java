package io.tictactoe.controller.db;

import io.tictactoe.controller.domain.board.Board;
import io.tictactoe.controller.domain.board.BoardPlayer;
import io.tictactoe.controller.domain.board.PlayerFrontend;
import io.tictactoe.controller.domain.state.GameState;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.NotFoundException;

import javax.inject.Inject;

public class MatchController {
    @Inject
    private GameState g;

    String newGameWaitingRoom = null;
    public String newMatch(Usuario u) throws NotFoundException {
        if (newGameWaitingRoom != null) {
            PlayerFrontend f = g.getPlayerFrontend(newGameWaitingRoom);
            f.setUsuario(u);
            String ret = newGameWaitingRoom;
            newGameWaitingRoom = null;
            return ret;
        }
        Board board = new Board();
        String xid = g.putPlayerFrontend(new PlayerFrontend(null, board, BoardPlayer.X));
        String oid = g.putPlayerFrontend(new PlayerFrontend(u, board, BoardPlayer.O));
        newGameWaitingRoom = xid;
        return oid;
    }

}
