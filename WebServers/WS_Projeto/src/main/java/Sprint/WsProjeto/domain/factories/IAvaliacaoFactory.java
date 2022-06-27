package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;

public interface IAvaliacaoFactory {
    Avaliacao createAvaliacao(int nCodMA, Juri oJuri, Submissao oSubmissao);
}
