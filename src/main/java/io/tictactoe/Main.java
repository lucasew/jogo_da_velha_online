package io.tictactoe;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.tictactoe.utils.AppLogger;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@QuarkusMain
public class Main {
    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.addHandler(new AppLogger());
        Quarkus.run(args);
    }
}
