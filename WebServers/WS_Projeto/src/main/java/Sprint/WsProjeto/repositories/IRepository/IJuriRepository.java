package Sprint.WsProjeto.repositories.IRepository;

import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;

import java.util.Optional;

public interface IJuriRepository {

    Juri save (Juri juri);

 Optional<Juri> findById(int codJuri);
}
