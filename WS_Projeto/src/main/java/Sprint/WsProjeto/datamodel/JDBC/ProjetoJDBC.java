package Sprint.WsProjeto.datamodel.JDBC;

import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjetoJDBC {

    //ordem retornada pelo PLSQL
    public static final int COD_PROJETO = 1;
    public static final int COD_PROPOSTA = 2;
    public static final int COD_ESTUDANTE = 3;
    public static final int COD_ORIENTADOR = 4;
    public static final int ESTADO = 5;


    public static final int ESTADO_POR_OMISSAO = Projeto.Estado.PENDENTE.ordinal();

    private int codProjeto;

    private int codProposta;


    private int codEstudante;


    private int codOrientador;

    private int estado;
}
