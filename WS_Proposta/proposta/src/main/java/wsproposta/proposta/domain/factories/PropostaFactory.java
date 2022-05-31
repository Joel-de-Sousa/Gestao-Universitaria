package wsproposta.proposta.domain.factories;

import org.springframework.stereotype.Service;
import wsproposta.proposta.domain.entities.Proposta;

@Service
public class PropostaFactory implements IPropostaFactory{

    public Proposta createProposta (int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo){
        return new Proposta(codUtilizador, nifOrganizacao, codEdicao, sTitulo, sProblema, sObjetivo);
    }

}
