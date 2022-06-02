package com.project.sprint.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
public class NewUtilizadorInfoDTO {
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

    public NewUtilizadorInfoDTO(String nome, String sobrenome, String email, String tipoUtilizador) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }
}
