package org.example.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@EqualsAndHashCode
public class AnoLetivoRestDTO {

    int codAnoLetivo;
    int ano;

    //adicionar o compareTo?

}
