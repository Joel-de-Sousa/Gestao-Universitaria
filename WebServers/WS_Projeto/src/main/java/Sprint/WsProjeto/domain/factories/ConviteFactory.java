package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import org.springframework.stereotype.Service;

@Service
public class ConviteFactory implements IConviteFactory{

        public Convite createConvite(int nCodProjeto, int nCodEstudante, int nCodDocente) {
            return new Convite(nCodProjeto,nCodEstudante,nCodDocente);
        }
    }
