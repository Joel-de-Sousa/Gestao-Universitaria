package Sprint.WsProjeto.datamodel.JDBC;

import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Submissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
@Setter
public class SubmissaoJDBC {
    //ordem retornada pelo PLSQL
    public static final int COD_SUBMISSAO = 1;
    public static final int TITULO = 2;
    public static final int URL_FICHEIRO = 3;
    public static final int LINGUAGEM_FICHEIRO = 4;
    public static final int ESTADO = 5;

    private static final int ESTADO_POR_OMISSAO = Submissao.Estado.PENDENTE.ordinal();

    private int codSubmissao;

    private String titulo;

    private String urlFicheiro;

    private String linguagemFicheiro;

    private int estado;
}
