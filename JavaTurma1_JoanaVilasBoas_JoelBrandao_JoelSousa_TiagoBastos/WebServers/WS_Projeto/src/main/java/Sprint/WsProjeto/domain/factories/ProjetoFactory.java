package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Projeto;
import org.springframework.stereotype.Service;


@Service
public class ProjetoFactory implements IProjetoFactory {


    public Projeto createProjeto(int nCodEstudante, int nCodOrientador, int nCodProposta) {
        return new Projeto(nCodEstudante, nCodOrientador, nCodProposta);
    }
}
