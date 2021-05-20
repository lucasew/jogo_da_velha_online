package io.tictactoe.controller.domain.state;

import io.tictactoe.controller.db.HistoricoController;
import io.tictactoe.controller.db.UsuarioController;
import io.tictactoe.controller.domain.board.Board;
import io.tictactoe.controller.domain.eventloop.EventLoopAction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class GameState implements Runnable {
    // Infraestrutura de controle da aplicação
    public Map<String, Board> matches = new HashMap<>();
    public ArrayBlockingQueue<EventLoopAction> eventLoop = new ArrayBlockingQueue<>(100);

    // Controllers (local padronizado)
    public HistoricoController historicoController = new HistoricoController();
    public UsuarioController usuarioController = new UsuarioController();

    // Controle de concorrência, talvez seja até desnecessário
    boolean isStop = false;

    public GameState() {
    }

    public void run() {
        while(!isStop) {
            System.out.println("Game state tick");
            try {
                EventLoopAction action = eventLoop.poll();
                if (action == null) { // se sem ação então a mimir
                    Thread.sleep(100);
                }
            } catch(Exception e) {
                System.out.println("Execução de item do event loop falhou");
                e.printStackTrace();
            }
        }
    }
}
