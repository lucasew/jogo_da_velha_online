package io.tictactoe.model.errors;

public class NotYourTurnException extends AppException {
    public NotYourTurnException() {
        super("Não é a sua vez");
    }
}
