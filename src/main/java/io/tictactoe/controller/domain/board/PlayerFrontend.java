package io.tictactoe.controller.domain.board;

import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.GameLogicException;
import io.tictactoe.model.errors.NotYourTurnException;

public class PlayerFrontend {
    Board board;
    BoardPlayer boardPlayer;
    Usuario usuario;

    public PlayerFrontend(Usuario usuario, Board board, BoardPlayer boardPlayer) {
        this.board = board;
        this.boardPlayer = boardPlayer;
        this.usuario = usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Board getBoard() {
        return board;
    }

    public void play(int position) throws GameLogicException, NotYourTurnException {
        board.play(boardPlayer, position);
    }
}
