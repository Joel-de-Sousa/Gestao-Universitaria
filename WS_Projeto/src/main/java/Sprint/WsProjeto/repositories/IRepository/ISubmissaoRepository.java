package Sprint.WsProjeto.repositories.IRepository;


import Sprint.WsProjeto.domain.entities.Submissao;

import java.sql.SQLException;

public interface ISubmissaoRepository {

    Submissao save (Submissao projeto) throws SQLException;


}
