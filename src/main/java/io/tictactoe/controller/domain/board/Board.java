package io.tictactoe.controller.domain.board;

import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.GameLogicException;
import io.tictactoe.model.errors.NotYourTurnException;

import java.time.Instant;

public class Board {
    public long lastAction;
    private BoardPlayer turn = BoardPlayer.X; // X sempre começa

    private BoardPlayer[] posicoes = {
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    };

    public BoardPlayer[] getPosicoes() {
        return posicoes;
    }

    public Board() {
        this.updateLastAction();
    }

    /***
     * Atualiza o unix timestamp da ultima ação, isso ai é para lidar com partidas abandonadas
     */
    private void updateLastAction() {
        this.lastAction = Instant.now().getEpochSecond();
    }
    /**
     * Checa vencedor
     * TODO: Implementar checagens
     * X se x ganhou
     * O se o ganhou
     * null se velha ou não definido
     * @return vencedor
     */
    public BoardResult checkWinner(BoardPlayer player) {
        return BoardResult.INCOMPLETO;
    }

    public void play(BoardPlayer player, int position) throws GameLogicException, NotYourTurnException {
        if (turn != player) {
            throw new NotYourTurnException();
        }
        try {
            throw new GameLogicException("Não implementado (ainda)");
        } finally { // Sempre vai executar depois do bloco de cima sair
            this.updateLastAction();
        }
    }
}
