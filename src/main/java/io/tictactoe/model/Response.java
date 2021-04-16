package io.tictactoe.model;

import java.io.Serializable;

public class Response<T> implements Serializable {
    public T data;
    public String error;
    public Response() {}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
