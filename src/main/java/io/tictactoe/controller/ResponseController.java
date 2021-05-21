package io.tictactoe.controller;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.tictactoe.model.errors.*;

import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

@RegisterForReflection
public class ResponseController<T> implements Callable<Response> {
    private static final Logger LOGGER = Logger.getLogger(ResponseController.class.getName());
    private final Callable<T> action;
    public ResponseController(Callable<T> action) {
        this.action = action;
    }

    @Override
    public Response call() {
        try {
            return Response.ok(action.call()).build();
        }
        catch (GameLogicException e) {
            return Response.status(400).entity("Jogada inválida").build();
        }
        catch (NotYourTurnException e) {
            return Response.status(400).entity("Não é a sua vez").build();
        }
        catch (NotFoundException e) {
            return Response.status(404, "Item não encontrado").build();
        }
        catch (BadRequestException e) {
            return Response.status(400, "Bad request").build();
        }
        catch (AppException e) {
            return Response.status(500, String.format("ERRO DE APLICAÇÃO: %s", e.getLocalizedMessage())).build();
        }
        catch (Throwable e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            e.printStackTrace();
            return Response.status(500, e.getLocalizedMessage()).build();
        }
    }
}
