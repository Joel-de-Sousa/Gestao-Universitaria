package wsproposta.proposta.DTO;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class CandidaturaDTO {

    @Getter
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    String estadoEstudante;
}
