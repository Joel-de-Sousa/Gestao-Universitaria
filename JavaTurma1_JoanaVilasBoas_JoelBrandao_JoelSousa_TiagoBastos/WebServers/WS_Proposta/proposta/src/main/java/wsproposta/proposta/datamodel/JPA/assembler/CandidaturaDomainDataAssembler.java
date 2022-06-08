package wsproposta.proposta.datamodel.JPA.assembler;

import org.springframework.stereotype.Service;
import wsproposta.proposta.datamodel.JPA.CandidaturaJPA;
import wsproposta.proposta.domain.entities.Candidatura;

@Service
public class CandidaturaDomainDataAssembler {

    public CandidaturaJPA toData (Candidatura candidatura){
        return new CandidaturaJPA(candidatura.getCodProposta(), candidatura.getCodEstudante(), candidatura.getCodOrientador(),
                candidatura.getEstadoEstudante(), candidatura.getEstadoOrientador());
    }

    public Candidatura toDomain (CandidaturaJPA candidaturaJPA){
        return new Candidatura(candidaturaJPA.getCodCandidatura(), candidaturaJPA.getCodProposta(), candidaturaJPA.getCodEstudante(),
                candidaturaJPA.getCodOrientador(), candidaturaJPA.getEstadoEstudante(), candidaturaJPA.getEstadoOrientador());
    }
}
