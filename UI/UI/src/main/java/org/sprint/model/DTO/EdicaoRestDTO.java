package org.sprint.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EdicaoRestDTO {
    @Getter
    int codEdicao;
    @Getter
    int codUc;
    @Getter
    String sigla;
    @Getter
    String denominacao;
    @Getter
    int codAnoLetivo;
    @Getter
    String ano;

    public EdicaoRestDTO(int codUc, int codAnoLetivo) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
    }
}
