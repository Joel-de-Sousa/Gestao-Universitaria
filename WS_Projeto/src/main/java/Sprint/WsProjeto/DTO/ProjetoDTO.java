package Sprint.WsProjeto.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class ProjetoDTO {

    @Getter

    private int codProjeto;

    @Getter

    private int codProposta;

    @Getter

    private int codEstudante;

    @Getter

    private int codOrientador;
}
