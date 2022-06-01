package wsproposta.proposta.DTO.assemblers;

import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.PropostaDTO;

@Service
public class PropostaDomainDTOAssembler {

    private PropostaDomainDTOAssembler(){}

    public PropostaDTO toDTO (int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo, String estado){
        return new PropostaDTO(codUtilizador, nifOrganizacao, codEdicao, sTitulo, sProblema, sObjetivo, estado);
    }
}
