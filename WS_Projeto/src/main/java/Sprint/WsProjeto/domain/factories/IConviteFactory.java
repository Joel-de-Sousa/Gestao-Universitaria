package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Convite;


public interface IConviteFactory {
    Convite createConvite(int nCodProjeto, int nCodEstudante, int nCodDocente);
}
