package wsproposta.proposta.datamodel.JPA.assembler;

import org.springframework.stereotype.Service;
import wsproposta.proposta.datamodel.JPA.PropostaJPA;
import wsproposta.proposta.domain.entities.Proposta;

@Service
public class PropostaDomainDataAssembler {

    public PropostaJPA toData (Proposta proposta){
        return new PropostaJPA(proposta.getCodProposta(),proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado());
    }

    public Proposta toDomain (PropostaJPA propostaJPA){
        return new Proposta(propostaJPA.getCodProposta(), propostaJPA.getCodUtilizador(), propostaJPA.getNifOrganizacao(), propostaJPA.getCodEdicao(),
                propostaJPA.getTitulo(), propostaJPA.getProblema(), propostaJPA.getObjetivo(), propostaJPA.getEstado());
    }
}
