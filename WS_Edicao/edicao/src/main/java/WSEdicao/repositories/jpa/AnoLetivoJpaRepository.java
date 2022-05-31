package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.AnoLetivoJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnoLetivoJpaRepository extends CrudRepository<AnoLetivoJpa, Integer>{

    Optional<AnoLetivoJpa> findBycodAnoLetivo(int codAnoLetivo);

    List<AnoLetivoJpa> findAll();

    //Optional<AnoLetivoJpa> save();
}
