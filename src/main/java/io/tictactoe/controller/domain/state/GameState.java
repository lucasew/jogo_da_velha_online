package io.tictactoe.controller.domain.state;

import io.tictactoe.controller.db.HistoricoController;
import io.tictactoe.controller.db.UsuarioController;
import io.tictactoe.controller.domain.board.PlayerFrontend;
import io.tictactoe.model.errors.GameLogicException;
import io.tictactoe.model.errors.NotFoundException;
import io.tictactoe.model.errors.NotYourTurnException;
import io.tictactoe.utils.MatchIDGenerator;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    // Infraestrutura de controle da aplicação
    public Map<String, PlayerFrontend> matches = new HashMap<>();

    public synchronized void playMatch(String room, int position) throws GameLogicException, NotYourTurnException {
        matches.get(room).play(position);
    }

    public synchronized String putPlayerFrontend(PlayerFrontend f) {
        String id = MatchIDGenerator.generateMatchID();
        matches.put(id, f);
        return id;
    }

    public synchronized PlayerFrontend getPlayerFrontend(String key) throws NotFoundException {
        PlayerFrontend res = matches.getOrDefault(key, null);
        if (res == null) {
            throw new NotFoundException();
        }
        return res;
    }

    // Controllers (local padronizado)
    public HistoricoController historicoController = new HistoricoController();
    public UsuarioController usuarioController = new UsuarioController();

    public GameState() {}
}
