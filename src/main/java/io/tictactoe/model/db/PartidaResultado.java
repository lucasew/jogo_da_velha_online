package io.tictactoe.model.db;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum PartidaResultado {
    DESISTENCIA,
    EMPATOU,
    GANHOU,
    PERDEU
}
