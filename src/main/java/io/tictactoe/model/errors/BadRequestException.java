package io.tictactoe.model.errors;

public class BadRequestException extends AppException {
    public BadRequestException() {
        super("Bad Request");
    }
}
