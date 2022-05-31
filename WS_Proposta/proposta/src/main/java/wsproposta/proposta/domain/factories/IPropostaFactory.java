package wsproposta.proposta.domain.factories;

import wsproposta.proposta.domain.entities.Proposta;

public interface IPropostaFactory {

    public Proposta createProposta (int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjectivo);
}
