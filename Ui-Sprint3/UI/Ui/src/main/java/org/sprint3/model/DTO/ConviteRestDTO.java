package org.sprint3.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ConviteRestDTO {

    @Getter
    int codConvite;
    @Getter
    int codEstudante;
    @Getter
    int codDocente;
    @Getter
    int codProjeto;

    public ConviteRestDTO(int codEstudante, int codDocente, int codProjeto) {
        this.codEstudante = codEstudante;
        this.codDocente = codDocente;
        this.codProjeto = codProjeto;
    }
}
