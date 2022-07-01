package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UtilizadorRestDTO {

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

    public UtilizadorRestDTO(String nome, String sobrenome, String email, String tipoUtilizador) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }
}
