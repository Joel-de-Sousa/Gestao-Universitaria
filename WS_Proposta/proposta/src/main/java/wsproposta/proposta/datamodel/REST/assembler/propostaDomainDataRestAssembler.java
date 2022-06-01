package wsproposta.proposta.datamodel.REST.assembler;

import org.springframework.stereotype.Service;
import wsproposta.proposta.datamodel.REST.PropostaRestDTO;
import wsproposta.proposta.domain.entities.Proposta;

@Service
public class PropostaDomainDataRestAssembler {

    public PropostaRestDTO toData (Proposta proposta){
        return new PropostaRestDTO(proposta.getCodUtilizador(),proposta.getNifOrganizacao(),
                proposta.getCodEdicao(), proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado());
    }

    public Proposta toDomain (PropostaRestDTO propostaRestDTO){
        return new Proposta(propostaRestDTO.getCodUtilizador(), propostaRestDTO.getNifOrganizacao(), propostaRestDTO.getCodEdicao(),
                propostaRestDTO.getTitulo(), propostaRestDTO.getProblema(), propostaRestDTO.getObjetivo()/*, propostaRestDTO.getEstado()*/);
    }
}
