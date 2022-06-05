package wsproposta.proposta.domain.factories;

import org.springframework.stereotype.Service;
import wsproposta.proposta.domain.entities.Proposta;

@Service
public class PropostaFactory implements IPropostaFactory{


    /**
     * Metodo para criar um objecto do tipo Proposta com os parametros codigo de utilizador, NIF da organizacao,
     * codigo da edicao, titulo, problema e objectivo
     * @param codUtilizador codigo do utilizador
     * @param nifOrganizacao NIF da organizacao
     * @param codEdicao codigo da edicao de unidade curricular
     * @param sTitulo titulo da proposta
     * @param sProblema problema da proposta
     * @param sObjetivo objectivo da proposta
     * @return umobjecto do tipo Proposta
     */

    public Proposta createProposta (int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo){
        return new Proposta(codUtilizador, nifOrganizacao, codEdicao, sTitulo, sProblema, sObjetivo);
    }

}
