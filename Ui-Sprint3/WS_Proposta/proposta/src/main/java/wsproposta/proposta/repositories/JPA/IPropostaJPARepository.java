package wsproposta.proposta.repositories.JPA;

import org.springframework.data.repository.CrudRepository;
import wsproposta.proposta.datamodel.JPA.PropostaJPA;

import java.util.List;
import java.util.Optional;

public interface IPropostaJPARepository extends CrudRepository<PropostaJPA, Integer> {

    Optional<PropostaJPA> findById(int codProposta);

    void deleteById (int codProposta);

    List<PropostaJPA> findAll();

    List<PropostaJPA> findAllByCodUtilizador (int codUtilizador);

    List<PropostaJPA> findAllByNifOrganizacao (long nr);

    List<PropostaJPA> findByTituloContains (String titulo);

    List<PropostaJPA> findAllByCodEdicao (int codEdicao);

}
