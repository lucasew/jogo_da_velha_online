package io.tictactoe.model.errors;

public class UserNotFoundException extends AppException {
    public UserNotFoundException() {
        super("Usuário não encontrado na base");
    }
}
