package Sprint.WsProjeto.repositories.JPA;

import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JuriJPARepository extends CrudRepository<JuriJPA, Integer> {


    Optional<JuriJPA> findById(int codJuri);
}