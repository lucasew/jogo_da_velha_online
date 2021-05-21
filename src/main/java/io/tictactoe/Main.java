package io.tictactoe;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.tictactoe.controller.db.HistoricoController;
import io.tictactoe.utils.AppLogger;

import javax.enterprise.context.ApplicationScoped;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@QuarkusMain
public class Main {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
