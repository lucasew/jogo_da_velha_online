package io.tictactoe.controller.domain;

import io.tictactoe.controller.db.HistoricoController;
import io.tictactoe.controller.db.UsuarioController;
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
import io.tictactoe.utils.AppLogger;
import io.tictactoe.utils.MatchIDGenerator;
import io.tictactoe.utils.Pair;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Singleton
public class MatchController {
    private static final Logger LOGGER = Logger.getLogger(MatchController.class.getName());
    private Map<String, PlayerFrontend> matches = new HashMap<>();
    private Map<String, String> adversaries = new HashMap<>();

    public MatchController() {}

    @Inject
    HistoricoController hc;

    public synchronized PlayerFrontend getPlayerFrontend(String room) throws NotFoundException {
//        System.out.println(String.format("'%s'", room));
        PlayerFrontend front = this.matches.get(room);
        if (front == null) {
            throw new NotFoundException();
        }
        return front;
    }

    private String getAdversaryFrontendID(String room) throws NotFoundException {
        String ret = adversaries.getOrDefault(room, null);
        if (ret == null) {
            LOGGER.warning(String.format("adversário de %s não encontrado", room));
            throw new NotFoundException();
        }
        return ret;
    }

    public synchronized String getAdversaryName(String room) throws NotFoundException {
//        LOGGER.info(String.format("frontends = %d, adversarios = %d", this.matches.size(), this.adversaries.size()));
        String adversary = this.getAdversaryFrontendID(room);
        PlayerFrontend f = this.getPlayerFrontend(adversary);
        Usuario u = f.getUsuario();
        String ret = u.getNome();
        return ret;
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
        this.getPlayerFrontend(room.second).setUsuario(u);
        return room.second;
    }
    private boolean isMatchDone(String room) throws NotFoundException {
        PlayerFrontend f = this.getPlayerFrontend(room);
        BoardResult result = f.getBoardResult();
        if (result != BoardResult.INCOMPLETO && result != BoardResult.NOT_STARTED) {
            return true;
        }
        long unix = Instant.now().getEpochSecond();
        long diff = unix - f.getBoard().lastAction;
        return diff > 30;
    }
    private void handleEndOfMatchIfEnded(String room) throws NotFoundException {
        if (isMatchDone(room)) {
            LOGGER.info("Room " + room + " e seu adversário terminaram partida.");
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
        LOGGER.info(String.format("Play: %s %d", room, position));
        handleEndOfMatchIfEnded(room);
        PlayerFrontend f = this.getPlayerFrontend(room);
        f.play(position);
    }
}
