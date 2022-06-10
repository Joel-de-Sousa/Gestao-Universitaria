package wsproposta.proposta.DTO.assemblers;

import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.CandidaturaDTO;
import wsproposta.proposta.domain.entities.Candidatura;

@Service

public class CandidaturaDomainDTOAssembler {

    private CandidaturaDomainDTOAssembler(){}

    public CandidaturaDTO toDTO (int codProposta, int codEstudante, int codOrientador, String estadoEstudante, String estadoOrientador){
        return new CandidaturaDTO(codProposta, codEstudante, estadoEstudante);
    }

    public CandidaturaDTO toDTO (Candidatura candidatura){
        return new CandidaturaDTO(candidatura.getCodProposta(), candidatura.getCodEstudante(),
               candidatura.getEstadoEstudante().toString());
    }
}
