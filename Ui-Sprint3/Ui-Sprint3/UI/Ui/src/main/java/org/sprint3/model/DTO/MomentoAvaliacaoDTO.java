package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class MomentoAvaliacaoDTO {

    int codMomentoAvaliacao;
    int codEdicao;
    String denominacao;

    public MomentoAvaliacaoDTO() {
    }

    public MomentoAvaliacaoDTO(int codEdicao, String denominacao) {
        this.codEdicao = codEdicao;
        this.denominacao = denominacao;
    }
}
