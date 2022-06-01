package wsproposta.proposta.domain.factories;

import org.springframework.stereotype.Service;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.domain.entities.Proposta.Estado;

@Service
public class PropostaFactory implements IPropostaFactory{

    public Proposta createProposta (int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo/*, Estado estado*/){
        return new Proposta(codUtilizador, nifOrganizacao, codEdicao, sTitulo, sProblema, sObjetivo/*, estado*/);
    }

}
