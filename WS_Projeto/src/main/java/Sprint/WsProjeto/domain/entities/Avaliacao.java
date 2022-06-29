package Sprint.WsProjeto.domain.entities;


import lombok.*;

import java.sql.Date;



@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Avaliacao {

    public static final int NOTA_INDEFINIDA=-1;
    public static final Juri JURI_POR_OMISSAO=null;
    public static final Submissao SUBMISSAO_POR_OMISSAO=null;
    public static final Date DATA_POR_OMISSAO=null;
    public static final Estado ESTADO_POR_OMISSAO=Estado.PENDENTE;
    public enum Estado {PENDENTE, CONCLUIDA, REVISAO}

    int codAvaliacao;

    int codProjeto;
    int codMA;
    Juri juri;
    Submissao submissao;

    double nota;

    String justificacao;

    Date date;

    Estado estado;

    public Avaliacao(int codMA, Juri juri, Submissao submissao) {
        this.codMA = codMA;
        this.juri = juri;
        this.submissao = submissao;
    }

    public Avaliacao(int codMA) {
        this.codMA = codMA;
    }
}
