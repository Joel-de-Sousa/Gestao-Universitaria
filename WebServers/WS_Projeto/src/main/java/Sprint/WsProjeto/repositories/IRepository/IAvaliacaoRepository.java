package Sprint.WsProjeto.repositories.IRepository;

import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;

import java.util.Optional;

public interface IAvaliacaoRepository {

    Avaliacao save (Avaliacao avaliacao);

 Optional<Avaliacao> findById(int codAvaliacao);
}
