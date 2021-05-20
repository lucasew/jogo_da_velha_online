package io.tictactoe.controller.domain.eventloop;

import io.tictactoe.controller.domain.state.GameState;

public interface EventLoopAction {
    void runAction(GameState state);
}
