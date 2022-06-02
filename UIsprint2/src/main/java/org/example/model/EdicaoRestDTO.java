package org.example.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.StandardException;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EdicaoRestDTO {

    int codEdicao;
    int codUc;
    int codAnoLetivo;
}
