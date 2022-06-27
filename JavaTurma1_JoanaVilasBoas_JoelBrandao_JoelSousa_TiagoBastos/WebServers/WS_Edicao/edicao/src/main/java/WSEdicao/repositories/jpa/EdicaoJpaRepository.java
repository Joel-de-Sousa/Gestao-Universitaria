package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.EdicaoJpa;

import WSEdicao.datamodel.EstudanteJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EdicaoJpaRepository extends CrudRepository<EdicaoJpa, Integer> {

    Optional<EdicaoJpa> findBycodEdicao(int codEdicao);
    Optional<EdicaoJpa> findByCodRUC(int codRUC);
    List<EdicaoJpa> findAll();

    List<EdicaoJpa> findListEdicaoBycodRUC(int codRUC);
    List<EdicaoJpa> findListEdicaoBycodEdicao(int codEdicao);
    boolean existsByCodUc(int codUc);
    boolean existsByCodAnoLetivo(int codAnoLetivo);
}
