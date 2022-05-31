package wsproposta.proposta.repositories.JPA;

import org.springframework.data.repository.CrudRepository;
import wsproposta.proposta.datamodel.JPA.PropostaJPA;

import java.util.List;
import java.util.Optional;

public interface PropostaJPARepository extends CrudRepository<PropostaJPA, Integer> {

    Optional<PropostaJPA> findById(int codProposta);

    void deleteById (int codProposta);

    List<PropostaJPA> findAll();
}
