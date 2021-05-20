package io.tictactoe.controller;

import io.tictactoe.model.Response;
import io.tictactoe.model.errors.AppException;

import java.util.concurrent.Callable;

public class ResponseController<T> implements Callable<Response<T>> {
    private final Callable<T> action;
    public ResponseController(Callable<T> action) {
        this.action = action;
    }

    @Override
    public Response<T> call() {
        Response<T> res = new Response<>();
        try {
            res.data = action.call();
        }
        catch (AppException e) {
            res.error = String.format("ERRO DE APLICAÇÃO: %s", e.getLocalizedMessage());
        }
        catch (Throwable e) {
            res.error = e.getLocalizedMessage();
            e.printStackTrace();
        }
        return res;
    }
}
