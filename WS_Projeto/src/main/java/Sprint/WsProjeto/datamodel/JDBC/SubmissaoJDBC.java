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

    private static final int ESTADO_POR_OMISSAO = Submissao.Estado.PENDENTE.ordinal();

    private int codSubmissao;

    private String titulo;

    private String urlFicheiro;

    private String linguagemFicheiro;

    private int estado;
}
