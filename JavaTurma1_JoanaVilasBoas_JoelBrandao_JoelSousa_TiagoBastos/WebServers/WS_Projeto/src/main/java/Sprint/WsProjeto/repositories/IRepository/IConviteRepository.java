package Sprint.WsProjeto.repositories.IRepository;

import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;

import java.util.Optional;

public interface IConviteRepository {

    Convite save (Convite convite);

 Optional<Convite> findById(int codDocente);
}
