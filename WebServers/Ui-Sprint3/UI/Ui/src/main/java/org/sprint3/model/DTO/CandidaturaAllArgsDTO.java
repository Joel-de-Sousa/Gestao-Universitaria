package org.sprint3.model.DTO;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidaturaAllArgsDTO {

    @Getter
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    String titulo;
    @Getter
    String problema;
    @Getter
    String objectivo;
    /*@Getter
    String edicao;*/
    @Getter
    int codEstudante;
    @Getter
    String nomeEstudante;
    @Getter
    String sobrenomeEstudante;
    @Getter
    String estado;
}