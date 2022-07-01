package wsproposta.proposta.datamodel.JPA.assembler;

import org.springframework.stereotype.Service;
import wsproposta.proposta.datamodel.JPA.CandidaturaJPA;
import wsproposta.proposta.domain.entities.Candidatura;

@Service
public class CandidaturaDomainDataAssembler {

    public CandidaturaJPA toData (Candidatura candidatura){
        return new CandidaturaJPA( candidatura.getCodCandidatura(), candidatura.getCodProposta(), candidatura.getCodEstudante(),
                candidatura.getEstadoEstudante());
    }

    public Candidatura toDomain (CandidaturaJPA candidaturaJPA){
        return new Candidatura(candidaturaJPA.getCodCandidatura(), candidaturaJPA.getCodProposta(), candidaturaJPA.getCodEstudante(),
                candidaturaJPA.getEstadoEstudante());
    }
}
