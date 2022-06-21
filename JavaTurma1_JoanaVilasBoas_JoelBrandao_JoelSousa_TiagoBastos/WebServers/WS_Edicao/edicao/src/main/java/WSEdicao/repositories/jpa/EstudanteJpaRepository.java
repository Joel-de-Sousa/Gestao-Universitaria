package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.EstudanteJpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EstudanteJpaRepository extends CrudRepository<EstudanteJpa, Integer> {

    Optional<EstudanteJpa> findByCodEstudante(int codEstudante);

}
