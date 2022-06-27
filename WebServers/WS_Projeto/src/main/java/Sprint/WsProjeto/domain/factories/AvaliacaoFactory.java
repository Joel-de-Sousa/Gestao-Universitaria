package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoFactory implements IAvaliacaoFactory{

        public Avaliacao createAvaliacao(int nCodMA, Juri oJuri, Submissao oSubmissao) {
            return new Avaliacao(nCodMA,oJuri,oSubmissao);
        }
    }


