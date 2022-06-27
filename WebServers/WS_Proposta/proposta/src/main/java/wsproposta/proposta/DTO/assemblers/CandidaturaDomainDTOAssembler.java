package wsproposta.proposta.DTO.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.CandidaturaDTO;
import wsproposta.proposta.DTO.CandidaturaDTOAllArgs;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.repositories.PropostaRepository;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;

import java.util.Optional;

@Service

public class CandidaturaDomainDTOAssembler {

    @Autowired
    PropostaRepository propostaRepository;
    @Autowired
    UtilizadorRestRepository utilizadorRestRepository;

    private CandidaturaDomainDTOAssembler(){}

    public CandidaturaDTO toDTO (int codCandidatura, int codProposta, int codEstudante, String estadoEstudante){
        return new CandidaturaDTO(codCandidatura, codProposta, codEstudante, estadoEstudante);
    }

    public CandidaturaDTO toDTO (Candidatura candidatura){
        return new CandidaturaDTO(candidatura.getCodCandidatura(), candidatura.getCodProposta(), candidatura.getCodEstudante(),
               candidatura.getEstadoEstudante().toString());
    }


    public CandidaturaDTOAllArgs toDTOAllArgs (Candidatura candidatura){
        Optional<Proposta> proposta = propostaRepository.findById(candidatura.getCodProposta());
        Optional<UtilizadorRestDTO> estudante = utilizadorRestRepository.findUtilizadorByCodUtilizador(candidatura.getCodEstudante());


        return new CandidaturaDTOAllArgs(candidatura.getCodCandidatura(), candidatura.getCodProposta(), proposta.get().getTitulo(),
                proposta.get().getProblema(), proposta.get().getObjetivo(), candidatura.getCodEstudante(), estudante.get().getNome(),
                estudante.get().getSobrenome(), candidatura.getEstadoEstudante().toString());
    }
}
