package Sprint.WsProjeto.datamodel.JDBC;

import Sprint.WsProjeto.domain.entities.Convite;
import lombok.*;


@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class ConviteRecusadoJDBC {

    private static final int ESTADO_POR_OMISSAO = Convite.Estado.PENDENTE.ordinal();

    int codConvite;

    int codProjeto;

    int codEstudante;

    int codDocente;

    int estado;


}


