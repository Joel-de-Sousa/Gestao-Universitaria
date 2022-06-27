package Sprint.WsProjeto.repositories.JPA;



import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubmissaoJPARepository extends CrudRepository<SubmissaoJPA, Integer> {



    Optional<SubmissaoJPA> findById(int codSubmisao);
}