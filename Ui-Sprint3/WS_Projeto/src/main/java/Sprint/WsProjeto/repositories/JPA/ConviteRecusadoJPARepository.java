package Sprint.WsProjeto.repositories.JPA;

import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.ConviteRecusadoJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConviteRecusadoJPARepository extends CrudRepository<ConviteRecusadoJPA, Integer> {


    Optional<ConviteRecusadoJPA> findById(int codDocente);
}