package wsproposta.proposta.DTO.assemblers;

import wsproposta.proposta.DTO.PropostaDTO;

public class PropostaDomainDTOAssembler {

    private PropostaDomainDTOAssembler(){}

    public PropostaDTO toDTO (int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo){
        return new PropostaDTO(codUtilizador, nifOrganizacao, codEdicao, sTitulo, sProblema, sObjetivo);
    }
}
