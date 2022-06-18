package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.UcJpa;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EdicaoJpaRepository extends CrudRepository<EdicaoJpa, Integer> {

    Optional<EdicaoJpa> findBycodEdicao(int codEdicao);
    List<EdicaoJpa> findAll();
    boolean existsByCodUc(int codUc);
    boolean existsByCodAnoLetivo(int codAnoLetivo);

    boolean existsBycodEdicao(int codEdicao);



}
