package Sprint.WsProjeto.repositories.JPA;

import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConviteJPARepository extends CrudRepository<ConviteJPA, Integer> {


    Optional<ConviteJPA> findByCodDocente(int codDocente);

    Optional<ConviteJPA> findByCodConvite (int CodConvite);

    void deleteById(int codConvite);

}