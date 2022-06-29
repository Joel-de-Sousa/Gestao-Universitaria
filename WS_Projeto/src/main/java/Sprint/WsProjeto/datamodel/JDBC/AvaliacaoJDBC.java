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


    private int codAvaliacao;

    private int codProjeto;

    private int codMA;

    private int codJuri;

    private int codSubmissao;

    private double nota;

    private String justificacao;

    private Date date;

    private int estado;



}
