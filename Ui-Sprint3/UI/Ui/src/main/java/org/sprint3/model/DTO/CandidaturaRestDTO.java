package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CandidaturaRestDTO {

    @Getter
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    String estadoEstudante;

    public CandidaturaRestDTO(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }
}
