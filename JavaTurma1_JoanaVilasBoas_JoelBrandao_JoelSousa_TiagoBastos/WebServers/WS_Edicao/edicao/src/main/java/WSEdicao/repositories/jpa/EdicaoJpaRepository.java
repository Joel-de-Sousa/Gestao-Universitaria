package WSEdicao.repositories.jpa;

import WSEdicao.datamodel.EdicaoJpa;

import WSEdicao.datamodel.EstudanteJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EdicaoJpaRepository extends CrudRepository<EdicaoJpa, Integer> {

    Optional<EdicaoJpa> findBycodEdicao(int codEdicao);
    List<EdicaoJpa> findAll();

    List<EdicaoJpa> findListEdicaoBycodRUC(int codRUC);
    List<EdicaoJpa> findListEdicaoBycodEdicao(int codEdicao);
    //Optional<EdicaoJpa> findByCodRUC(int codRUC);
<<<<<<< HEAD
   /* List<EdicaoJpa> findListEstudantesJpaBylistEstudantes(List<EstudanteJpa> listEstudantes);*/
=======
/*
    List<EdicaoJpa> findListEstudantesJpaBylistEstudantes(List<EstudanteJpa> listEstudantes);
*/
>>>>>>> 8f452aaeb2bde24ccd182ec10b3c8f3fd6e42bda
    boolean existsByCodUc(int codUc);
    boolean existsByCodAnoLetivo(int codAnoLetivo);

 /*   List<EdicaoJpa> findAllBycodEstudante(int codEstudante);*/




}
