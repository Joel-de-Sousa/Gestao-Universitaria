package wsproposta.proposta.domain.factories;

import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.domain.entities.Proposta;

public class CandidaturaFactory implements ICandidaturaFactory{

    public Candidatura createCandidatura (int codProposta, int codEstudante, int codOrientador, Candidatura.Estado estadoEstudante, Candidatura.Estado estadoOrientador) {
        return new Candidatura(codProposta, codEstudante, codOrientador, estadoEstudante, estadoOrientador);

    }

}
