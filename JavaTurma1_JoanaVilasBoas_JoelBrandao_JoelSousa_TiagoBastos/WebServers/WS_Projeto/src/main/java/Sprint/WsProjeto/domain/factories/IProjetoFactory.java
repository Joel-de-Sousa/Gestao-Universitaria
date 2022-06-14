package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Projeto;

import java.util.List;

public interface IProjetoFactory {



    Projeto createProjeto(int nCodEstudante, int nCodOrientador, int nCodProposta);
}
