package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AnoLetivoRestDTO {


    @Getter
    int codAnoLetivo;
    @Getter
    String ano;

    public AnoLetivoRestDTO(String ano) {
        this.ano = ano;
    }
}
