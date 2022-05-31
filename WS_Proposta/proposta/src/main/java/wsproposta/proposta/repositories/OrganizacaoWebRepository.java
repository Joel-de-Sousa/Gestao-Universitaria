package wsproposta.proposta.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.repositories.REST.OrganizacaoRestRepository;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;

import java.util.Optional;

public class OrganizacaoWebRepository {

    @Autowired
    OrganizacaoRestRepository organizacaoRestRepository;

    public Optional<OrganizacaoRestDTO> findOrganizacaoByNifOrganizacao (int nifOrganizacao) {
        Optional<OrganizacaoRestDTO> opOrganizacao = organizacaoRestRepository.findOrganizacaoByNifOrganizacao(nifOrganizacao);

        if ( opOrganizacao.isPresent() ) {
            return opOrganizacao;
        }
        else
            return Optional.empty();
    }

}
