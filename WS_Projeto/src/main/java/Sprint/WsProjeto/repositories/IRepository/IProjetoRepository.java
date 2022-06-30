package Sprint.WsProjeto.repositories.IRepository;

import Sprint.WsProjeto.domain.entities.Projeto;

import java.sql.SQLException;
import java.util.Optional;

public interface IProjetoRepository {

    Projeto save (Projeto projeto) throws SQLException;

 Optional<Projeto> findById(int codProjeto) throws SQLException;
}
