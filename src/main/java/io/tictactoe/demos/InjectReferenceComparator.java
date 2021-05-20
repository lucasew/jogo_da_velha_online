package io.tictactoe.demos;

import io.tictactoe.controller.domain.state.GameState;

import javax.inject.Inject;

public class InjectReferenceComparator {
    public static void main(String[] args) {
        GamestateThat gthat = new GamestateThat();
        GamestateThis gthis = new GamestateThis();
        if (gthis.gameState == gthat.gameState) {
            System.out.println("Mesma referência");
        }
    }
}


class GamestateThat {
    @Inject
    GameState gameState;

    GameState getGameState() {
        return gameState;
    }
}


class GamestateThis {
    @Inject
    GameState gameState;

    GameState getGameState() {
        return gameState;
    }
}
