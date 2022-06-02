package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.UcJpa;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UcJpaRepository extends CrudRepository<UcJpa, Integer> {

    Optional<UcJpa> findBycodUc(int codUc);

    List<UcJpa> findAll();

}
