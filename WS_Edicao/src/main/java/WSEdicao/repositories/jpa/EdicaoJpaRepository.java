package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.EdicaoJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EdicaoJpaRepository extends CrudRepository<EdicaoJpa, Integer> {

    Optional<EdicaoJpa> findBycodEdicao(int codEdicao);

    List<EdicaoJpa> findAll();
}
