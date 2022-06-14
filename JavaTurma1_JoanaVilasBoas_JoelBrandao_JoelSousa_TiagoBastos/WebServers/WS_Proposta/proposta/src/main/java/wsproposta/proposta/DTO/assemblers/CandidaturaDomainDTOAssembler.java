package wsproposta.proposta.DTO.assemblers;

import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.CandidaturaDTO;
import wsproposta.proposta.domain.entities.Candidatura;

@Service

public class CandidaturaDomainDTOAssembler {

    private CandidaturaDomainDTOAssembler(){}

    public CandidaturaDTO toDTO (int codCandidatura, int codProposta, int codEstudante, String estadoEstudante){
        return new CandidaturaDTO(codCandidatura, codProposta, codEstudante, estadoEstudante);
    }

    public CandidaturaDTO toDTO (Candidatura candidatura){
        return new CandidaturaDTO(candidatura.getCodCandidatura(), candidatura.getCodProposta(), candidatura.getCodEstudante(),
               candidatura.getEstadoEstudante().toString());
    }
}
