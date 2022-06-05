package wsproposta.proposta.datamodel.JPA.assembler;

import org.springframework.stereotype.Service;
import wsproposta.proposta.datamodel.JPA.PropostaJPA;
import wsproposta.proposta.domain.entities.Proposta;

@Service
public class PropostaDomainDataAssembler {

    /**
     * Metodo que recebe como parametro um objecto Proposta e cria e retorna
     * um objecto PropostaJPA
     * @param proposta objecto do tipo Proposta
     * @return retorna um PropostaJPA criado
     */
    public PropostaJPA toData (Proposta proposta){
        return new PropostaJPA(proposta.getCodProposta(),proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado());
    }

    /**
     * Metodo que recebe como parametro um objecto PropostaJPA e cria e retorna
     * um objecto Proposta
     * @param propostaJPA objecto do tipo PropostaJPA
     * @return retorna um Proposta criado
     */
    public Proposta toDomain (PropostaJPA propostaJPA){
        return new Proposta(propostaJPA.getCodProposta(), propostaJPA.getCodUtilizador(), propostaJPA.getNifOrganizacao(), propostaJPA.getCodEdicao(),
                propostaJPA.getTitulo(), propostaJPA.getProblema(), propostaJPA.getObjetivo(), propostaJPA.getEstado());
    }
}
