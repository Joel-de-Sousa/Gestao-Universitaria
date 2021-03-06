package Sprint.WsProjeto.repositories.JPA;

import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConviteJPARepository extends CrudRepository<ConviteJPA, Integer> {


    Optional<ConviteJPA> findById(int codDocente);
    void deleteById(int codConvite);

}