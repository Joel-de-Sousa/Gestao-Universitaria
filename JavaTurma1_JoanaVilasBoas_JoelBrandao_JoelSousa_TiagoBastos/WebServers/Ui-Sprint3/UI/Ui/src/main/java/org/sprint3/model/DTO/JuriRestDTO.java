package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JuriRestDTO {

    @Getter
    String nomeOrientador;
    @Getter
    String nomeArguente;
    @Getter
    String nomePresidente;


}
