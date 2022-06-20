package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import org.springframework.stereotype.Service;

@Service
public class JuriFactory implements IJuriFactory{

        public Juri createJuri(int nCodPresidente,int nCodOrientador, int nCodArguente) {
            return new Juri(nCodPresidente,nCodOrientador,nCodArguente);
        }
    }

