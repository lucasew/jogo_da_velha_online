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

    private boolean checkCondition(BoardPlayer player, int pos) {
        return posicoes[pos] == player;
    }
    /***
     * Checa se triplas de casas foram setadas pelo usuário que foi passado
     * Baseado na lógica de: https://github.com/lucasew/playground/blob/master/facul/velha_graphviz.py
     * @param posa primeira posição
     * @param posb segunda posição
     * @param posc terceira posição
     * @return se todas essas casas foram atribuidas ao usuario
     */
    private boolean checkConditionTriple(BoardPlayer player, int posa, int posb, int posc) {
        return checkCondition(player, posa) && checkCondition(player, posb) && checkCondition(player, posc);
    }
    private boolean isVelha() {
        for (int i = 0; i < 9; i++) {
            if (this.posicoes[i] == null) {
                return false;
            }
        }
        return true;
    }
    public boolean isStarted() {
        for (int i = 0; i < 9; i++) {
            if (this.posicoes[i] != null) {
                return false;
            }
        }
        return true;
    }
    private boolean isPlayerWon(BoardPlayer player) {
        return checkConditionTriple(player, 0, 1, 2) // horizontal
                || checkConditionTriple(player, 3,4,5)
                || checkConditionTriple(player, 6,7,8)
                || checkConditionTriple(player, 0, 3 ,6) // vertical
                || checkConditionTriple(player, 1, 4, 7)
                || checkConditionTriple(player, 2, 5, 8)
                || checkConditionTriple(player, 0, 4, 8) // diagonal
                || checkConditionTriple(player, 2, 4, 6);
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
        if (!this.isStarted()) {
            return BoardResult.NOT_STARTED;
        }
        if (this.isPlayerWon(player)) {
            return BoardResult.WIN;
        }
        if (this.isPlayerWon(Board.togglePlayer(player))) {
            return BoardResult.LOSE;
        }
        if (this.isVelha()) {
            return BoardResult.EMPATE;
        }
        return BoardResult.INCOMPLETO;
    }

    public static BoardPlayer togglePlayer(BoardPlayer player) {
        if (player == BoardPlayer.X) {
            return BoardPlayer.O;
        }
        return BoardPlayer.X;
    }

    public void play(BoardPlayer player, int position) throws GameLogicException, NotYourTurnException {
        if (turn != player) {
            throw new NotYourTurnException();
        }
        try {
            if (position < 0 || position > 8) {
                throw new GameLogicException("Posição inválida de jogo");
            }
            if (this.posicoes[position] != null) {
                throw new GameLogicException("Esta posição já está ocupada");
            }
            this.posicoes[position] = player;
        } finally { // Sempre vai executar depois do bloco de cima sair
            this.updateLastAction();
            this.turn = Board.togglePlayer(this.turn);
        }
    }
}
