package io.tictactoe.model.errors;

public class AppException extends Exception {
    public AppException(String reason) {
        super(reason);
    }
}
