package wsproposta.proposta.domain.factories;

import org.springframework.stereotype.Service;
import wsproposta.proposta.domain.entities.Candidatura;

@Service
public class CandidaturaFactory implements ICandidaturaFactory{

    public Candidatura createCandidatura (int codProposta, int codEstudante) {
        return new Candidatura(codProposta, codEstudante);

    }

}
