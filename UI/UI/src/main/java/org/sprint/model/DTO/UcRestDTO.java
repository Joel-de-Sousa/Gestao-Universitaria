package org.sprint.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UcRestDTO {

    @Getter
    int codUc;
    @Getter
    String sigla;
    @Getter
    String denominacao;


}
