package wsproposta.proposta.DTO;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class CandidaturaDTO {

    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    int codOrientador;
    @Getter
    String estadoEstudante;
    @Getter
    String estadoOrientador;
}
