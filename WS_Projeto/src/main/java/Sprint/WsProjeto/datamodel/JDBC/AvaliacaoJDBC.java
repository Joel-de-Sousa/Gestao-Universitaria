package Sprint.WsProjeto.datamodel.JDBC;


import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import lombok.*;

import java.sql.Date;



@AllArgsConstructor
@Getter
@Setter
public class AvaliacaoJDBC {

    public static final int NOTA_INDEFINIDA = Avaliacao.NOTA_INDEFINIDA;
    public static final int ESTADO_POR_OMISSAO = Avaliacao.Estado.PENDENTE.ordinal();

    //ordem retornada pelo PLSQL
    public static final int COD_AVALIACAO = 1;
    public static final int COD_PROJETO = 2;
    public static final int COD_MA = 3;
    public static final int COD_JURI = 4;
    public static final int COD_SUBMISSAO = 5;
    public static final int NOTA = 6;
    public static final int JUSTIFICACAO = 7;
    public static final int DATE = 8;
    public static final int ESTADO = 9;


    private int codAvaliacao;

    private int codProjeto;

    private int codMA;

    private int codJuri;

    private int codSubmissao;

    private double nota;

    private String justificacao;

    private Date date;

    private int estado;


    public AvaliacaoJDBC(int codAvaliacao, int estado) {
        this.codAvaliacao = codAvaliacao;
        this.estado = estado;
    }

    public AvaliacaoJDBC(int codMA) {
        this.codMA = codMA;
    }
}
