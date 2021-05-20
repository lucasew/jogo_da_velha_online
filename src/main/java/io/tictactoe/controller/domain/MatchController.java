package io.tictactoe.controller.domain;

import io.tictactoe.controller.db.HistoricoController;
import io.tictactoe.controller.domain.board.Board;
import io.tictactoe.controller.domain.board.BoardPlayer;
import io.tictactoe.controller.domain.board.BoardResult;
import io.tictactoe.controller.domain.board.PlayerFrontend;
import io.tictactoe.model.db.Partida;
import io.tictactoe.model.db.PartidaResultado;
import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.GameLogicException;
import io.tictactoe.model.errors.NotFoundException;
import io.tictactoe.model.errors.NotYourTurnException;
import io.tictactoe.utils.MatchIDGenerator;
import io.tictactoe.utils.Pair;

import javax.inject.Inject;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class MatchController {
    private Map<String, PlayerFrontend> matches = new HashMap<>();
    private Map<String, String> adversaries = new HashMap<>();

    @Inject
    HistoricoController hc;

    public synchronized PlayerFrontend getPlayerFrontend(String key) throws NotFoundException {
        PlayerFrontend res = matches.getOrDefault(key, null);
        if (res == null) {
            throw new NotFoundException();
        }
        return res;
    }

    private Pair<String, String> generateMatchIDs() {
        return new Pair<>(
                MatchIDGenerator.generateMatchID(),
                MatchIDGenerator.generateMatchID()
        );
    }

    private Pair<String, String> createRoom() {
        Pair<String, String> ids = this.generateMatchIDs();
        Board board = new Board();
        matches.put(ids.first, new PlayerFrontend(board, BoardPlayer.X));
        matches.put(ids.second, new PlayerFrontend(board, BoardPlayer.O));
        adversaries.put(ids.first, ids.second);
        adversaries.put(ids.second, ids.first);
        return ids;
    }

    private String queuedFrontend = null;

    public synchronized  String getMatch(Usuario u) throws NotFoundException {
        if (queuedFrontend != null) {
            this.getPlayerFrontend(queuedFrontend).setUsuario(u);
            String ret = queuedFrontend;
            queuedFrontend = null;
            return ret;
        }
        Pair<String, String> room = createRoom();
        queuedFrontend = room.first;
        return room.second;
    }
    private boolean isMatchDone(String room) throws NotFoundException {
        PlayerFrontend f = this.getPlayerFrontend(room);
        if (f.getBoardResult() != BoardResult.INCOMPLETO) {
            return true;
        }
        long unix = Instant.now().getEpochSecond();
        long diff = unix - f.getBoard().lastAction;
        return diff > 30;
    }
    private void handleEndOfMatchIfEnded(String room) throws NotFoundException {
        if (isMatchDone(room)) {
            String adversary = this.adversaries.get(room);
            PlayerFrontend fx = this.getPlayerFrontend(room);
            PlayerFrontend fo = this.getPlayerFrontend(adversary);
            Usuario jx = fx.getUsuario();
            Usuario jo = fo.getUsuario();
            hc.putPartida(new Partida(jx, jo, fromBoardResult(fx.getBoardResult())));
            hc.putPartida(new Partida(jo, jx, fromBoardResult(fo.getBoardResult())));
            this.adversaries.remove(room);
            this.adversaries.remove(adversary);
            this.matches.remove(room);
            this.matches.remove(adversary);
        }
    }

    private PartidaResultado fromBoardResult(BoardResult br) {
        switch(br) {
            case EMPATE:
                return PartidaResultado.EMPATOU;
            case WIN:
                return PartidaResultado.GANHOU;
            case LOSE:
                return PartidaResultado.PERDEU;
        }
        return PartidaResultado.DESISTENCIA;
    }
    public synchronized void playMatch(String room, int position) throws GameLogicException, NotYourTurnException, NotFoundException {
        handleEndOfMatchIfEnded(room);
        PlayerFrontend f = this.getPlayerFrontend(room);
        f.play(position);
    }
}
