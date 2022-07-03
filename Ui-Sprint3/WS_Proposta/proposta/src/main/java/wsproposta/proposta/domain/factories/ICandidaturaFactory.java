package wsproposta.proposta.domain.factories;

import wsproposta.proposta.domain.entities.Candidatura;

public interface ICandidaturaFactory {

    public Candidatura createCandidatura (int codProposta, int codEstudante);

    }
