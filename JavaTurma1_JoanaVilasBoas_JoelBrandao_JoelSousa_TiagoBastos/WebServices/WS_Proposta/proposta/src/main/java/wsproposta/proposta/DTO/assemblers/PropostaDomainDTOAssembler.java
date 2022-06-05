package wsproposta.proposta.DTO.assemblers;

import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.domain.entities.Proposta;

@Service
public class PropostaDomainDTOAssembler {

    private PropostaDomainDTOAssembler(){}

    public PropostaDTO toDTO (int codUtilizador, long nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo, String estado){
        return new PropostaDTO(codUtilizador, nifOrganizacao, codEdicao, sTitulo, sProblema, sObjetivo, estado);
    }

    public PropostaDTO toDTO (Proposta proposta){
        return new PropostaDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
    }

    /*public Proposta toDomain (NewPropostaDTO propostaDTO){
        return new Proposta(propostaDTO.getCodProposta(), propostaDTO.getCodUtilizador(), propostaDTO.getNifOrganizacao(),
                propostaDTO.getCodEdicao(), propostaDTO.getTitulo(), propostaDTO.getProblema(), propostaDTO.getObjetivo(),  Proposta.Estado.valueOf(propostaDTO.getEstado()));
    }*/

    /*public Proposta toDomain (PropostaDTOParcial propostaDTO){
        return new Proposta(propostaDTO.getCodProposta(),  Proposta.Estado.valueOf(propostaDTO.getEstado()));
    }*/
}
