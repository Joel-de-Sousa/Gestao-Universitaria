package wsproposta.proposta.repositories.iRepositories;

import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;

import java.util.Optional;

public interface IOrganizacaoWebRepository {

    public Optional<OrganizacaoRestDTO> findOrganizacaoByNifOrganizacao(int nifOrganizacao);
}
