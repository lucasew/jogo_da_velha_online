package io.tictactoe.model.errors;

public class NotFoundException extends AppException {
    public NotFoundException() {
        super("Recurso não encontrado");
    }
}
