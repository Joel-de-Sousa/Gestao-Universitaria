package com.project.sprint.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@ToString

public class UtilizadorDTO {

    @Getter
    int codUtilizador;

    @Getter
    String nome;

    @Getter
    String sobrenome;

    @Getter
    String email;

    @Getter
    String tipoUtilizador;
}
