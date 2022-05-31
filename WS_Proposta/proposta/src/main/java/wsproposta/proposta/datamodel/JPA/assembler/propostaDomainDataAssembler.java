package wsproposta.proposta.datamodel.JPA.assembler;

import wsproposta.proposta.datamodel.JPA.PropostaJPA;
import wsproposta.proposta.domain.entities.Proposta;

public class PropostaDomainDataAssembler {

    public PropostaJPA toData (Proposta proposta){
        return new PropostaJPA(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo());
    }

    public Proposta toDomain (PropostaJPA propostaJPA){
        return new Proposta(propostaJPA.getCodUtilizador(), propostaJPA.getNifOrganizacao(), propostaJPA.getCodEdicao(),
                propostaJPA.getTitulo(), propostaJPA.getProblema(), propostaJPA.getObjetivo());
    }
}
