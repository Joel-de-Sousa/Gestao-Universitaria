package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Projeto;

public interface IProjetoFactory {



    Projeto createProjeto(int nCodProposta, int nCodEstudante);
}
