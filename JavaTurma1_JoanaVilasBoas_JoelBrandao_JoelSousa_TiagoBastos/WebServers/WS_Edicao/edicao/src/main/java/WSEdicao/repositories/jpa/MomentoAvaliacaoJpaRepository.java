package WSEdicao.repositories.jpa;



import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MomentoAvaliacaoJpaRepository extends CrudRepository<MomentoAvaliacaoJpa, Integer> {


    Optional<MomentoAvaliacaoJpa> findBycodMomentoAvaliacao(int codMomentoAvaliacao);
    List<MomentoAvaliacaoJpa> findAll();
    boolean existsByDenominacao(String denominacao);
    List<MomentoAvaliacaoJpa> findByCodEdicao(int codEdicao);




}
