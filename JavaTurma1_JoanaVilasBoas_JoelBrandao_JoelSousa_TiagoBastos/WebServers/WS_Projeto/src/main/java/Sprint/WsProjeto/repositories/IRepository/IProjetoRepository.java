package Sprint.WsProjeto.repositories.IRepository;

import Sprint.WsProjeto.domain.entities.Projeto;

import java.util.Optional;

public interface IProjetoRepository {

    Projeto save (Projeto projeto);

 Optional<Projeto> findById(int codProjeto);
}
