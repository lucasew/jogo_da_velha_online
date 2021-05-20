package io.tictactoe.model.errors;

public class GameLogicException extends AppException {
    public GameLogicException(String reason) {
        super(reason);
    }
    public GameLogicException() {
        super("Você fez uma jogada inválida!");
    }
}
