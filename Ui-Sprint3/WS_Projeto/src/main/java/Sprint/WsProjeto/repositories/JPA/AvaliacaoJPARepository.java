package Sprint.WsProjeto.repositories.JPA;

import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AvaliacaoJPARepository extends CrudRepository<AvaliacaoJPA, Integer> {


    Optional<AvaliacaoJPA> findById(int codAvaliacao);
}