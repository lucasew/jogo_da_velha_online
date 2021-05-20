package io.tictactoe.utils;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Pair<A, B> {
    public A first;
    public B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}
