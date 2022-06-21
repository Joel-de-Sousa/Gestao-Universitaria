package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.EstudanteJpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EstudanteJpaRepository extends CrudRepository<EstudanteJpa, Integer> {

    Optional<EstudanteJpa> findByCodEstudante(int codEstudante);
<<<<<<< HEAD
    /*EstudanteJpa findByCodEdicao(int codEdicao);
    List<EstudanteJpa> findListEstudanteBy(int codEstudante);*/
=======
    EstudanteJpa findByCodEdicao(int codEdicao);
/*
    List<EstudanteJpa> findListEstudanteBy(int codEstudante);
*/
>>>>>>> 8f452aaeb2bde24ccd182ec10b3c8f3fd6e42bda
}
