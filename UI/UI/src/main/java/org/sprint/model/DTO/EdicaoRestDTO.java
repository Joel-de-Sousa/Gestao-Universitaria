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
    int codAnoLetivo;

}
