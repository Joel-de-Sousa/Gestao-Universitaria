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

    /**
     * Metodo permite encontrar uma organizacao atraves do nifOrganizacao, comunica com
     * o RestRepositorio
     * @param nifOrganizacao é o identificador do NIF Organizacao na BD, definido como o NIF da organizacao
     * @return retorna um otpional de OrganizacaoRestDto
     */

    public Optional<OrganizacaoRestDTO> findOrganizacaoByNifOrganizacao (long nifOrganizacao) {
        Optional<OrganizacaoRestDTO> opOrganizacao = organizacaoRestRepository.findOrganizacaoByNifOrganizacao(nifOrganizacao);

        if ( opOrganizacao.isPresent() ) {
            return opOrganizacao;
        }
        else
            return Optional.empty();
    }

}
