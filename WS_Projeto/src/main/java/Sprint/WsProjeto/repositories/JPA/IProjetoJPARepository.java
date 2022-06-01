package Sprint.WsProjeto.repositories.JPA;

import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProjetoJPARepository extends CrudRepository<ProjetoJPA, Integer> {


    Optional<ProjetoJPA> findByCode(int nCode);
}