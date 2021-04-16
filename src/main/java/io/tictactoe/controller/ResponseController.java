package io.tictactoe.controller;

import io.tictactoe.model.Response;

import javax.ws.rs.BadRequestException;
import java.util.concurrent.Callable;

public class ResponseController<T> implements Callable<Response<T>> {
    private final Callable<T> action;

    public ResponseController(Callable<T> action) {
        this.action = action;
    }


    @Override
    public Response<T> call() {
        Response<T> res = new Response<T>();
        try {
            res.data = action.call();
        } catch (Throwable e) {
            res.error = e.getLocalizedMessage();
            e.printStackTrace();
        }
        finally {
            return res;
        }
    }
}
