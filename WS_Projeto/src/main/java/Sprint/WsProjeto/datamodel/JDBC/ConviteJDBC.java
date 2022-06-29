package Sprint.WsProjeto.datamodel.JDBC;

import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Convite;
import lombok.*;


@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class ConviteJDBC {

    private static final int ESTADO_POR_OMISSAO = Convite.Estado.PENDENTE.ordinal();
    //ordem retornada pelo PLSQL
    public static final int COD_CONVITE = 1;
    public static final int COD_PROJETO = 2;
    public static final int COD_ESTUDANTE = 3;
    public static final int COD_DOCENTE = 4;
    public static final int ESTADO = 5;


    int codConvite;

    int codProjeto;

    int codEstudante;

    int codDocente;

    int estado;


}


