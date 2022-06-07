package org.sprint.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UcRestDTO {

    @Getter
    int codUc;
    @Getter
    String sigla;
    @Getter
    String denominacao;

    public UcRestDTO(String sigla, String denominacao) {
        this.sigla = sigla;
        this.denominacao = denominacao;
    }
}
