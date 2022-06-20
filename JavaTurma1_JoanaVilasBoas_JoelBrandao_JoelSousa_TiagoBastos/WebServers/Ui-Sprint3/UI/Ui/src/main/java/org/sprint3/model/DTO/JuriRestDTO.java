package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JuriRestDTO {

    @Getter
    String nomePresidente;
    @Getter
    int codPresidente;
    @Getter
    String nomeOrientador;
    @Getter
    int codOrientador;
    @Getter
    String nomeArguente;
    @Getter
    int codArguente;

    public JuriRestDTO(String presidente, String orientador, String arguente) {
        this.codPresidente = codPresidente;
        this.codOrientador = codOrientador;
        this.codArguente = codArguente;
    }

   /* public JuriRestDTO(int codPresidente, int codOrientador, int codArguente) {
        this.codPresidente = codPresidente;
        this.codOrientador = codOrientador;
        this.codArguente = codArguente;
    }*/


}
