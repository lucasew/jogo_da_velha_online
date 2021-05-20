package io.tictactoe.controller.domain.board;

import io.tictactoe.model.errors.GameLogicException;

public class Board {
    private BoardPlayer[] posicoes = {
            null,
            null,
            null,
            null,
            null,
            null,
            null
    };
    public void play(BoardPlayer player, int position) throws GameLogicException {
        throw new GameLogicException("Não implementado (ainda)");
    }
}
