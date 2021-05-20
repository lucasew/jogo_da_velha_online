package io.tictactoe.controller.domain.board;

import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.GameLogicException;
import io.tictactoe.model.errors.NotFoundException;
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

    public  PlayerFrontend(Board board, BoardPlayer boardPlayer) {
        this(null, board, boardPlayer);
    }

    public BoardResult getBoardResult() {
        return board.checkWinner(boardPlayer);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Usuario getUsuario() throws NotFoundException {
        if (this.usuario == null) {
            throw new NotFoundException();
        }
        return this.usuario;
    }

    public Board getBoard() {
        return board;
    }

    public void play(int position) throws GameLogicException, NotYourTurnException {
        board.play(boardPlayer, position);
    }
}
