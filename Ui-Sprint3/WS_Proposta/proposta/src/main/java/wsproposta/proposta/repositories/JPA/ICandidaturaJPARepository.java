package wsproposta.proposta.repositories.JPA;

import org.springframework.data.repository.CrudRepository;
import wsproposta.proposta.datamodel.JPA.CandidaturaJPA;

import java.util.List;
import java.util.Optional;

public interface ICandidaturaJPARepository extends CrudRepository<CandidaturaJPA, Integer> {

    Optional<CandidaturaJPA> findById(int codCandidatura);

    Optional<CandidaturaJPA> findByCodEstudante(int codEstudante);


    void deleteById (int codCandidatura);

    List<CandidaturaJPA> findAll();

}
