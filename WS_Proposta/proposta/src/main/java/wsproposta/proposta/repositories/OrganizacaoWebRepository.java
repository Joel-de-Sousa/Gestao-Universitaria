package wsproposta.proposta.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.repositories.REST.OrganizacaoRestRepository;
import wsproposta.proposta.repositories.iRepositories.IOrganizacaoWebRepository;

import java.util.Optional;

@Repository
public class OrganizacaoWebRepository implements IOrganizacaoWebRepository {

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
