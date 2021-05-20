package io.tictactoe.controller.domain.state;

public class GameStateSingleton {
    private static GameState gameState;
    public static GameState getGameState() {
        if (GameStateSingleton.gameState == null) {
            GameStateSingleton.gameState = new GameState();
            new Thread(GameStateSingleton.gameState).start();
        }
        return GameStateSingleton.gameState;
    }
}
