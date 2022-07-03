package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;

public interface IJuriFactory {
    Juri createJuri(int nCodPresidente, int nCodOrientador, int nCodArguente);
}
