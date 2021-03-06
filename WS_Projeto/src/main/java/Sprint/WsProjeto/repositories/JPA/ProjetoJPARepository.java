package Sprint.WsProjeto.repositories.JPA;

import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;

import org.springframework.data.repository.CrudRepository;



import java.util.Optional;

public interface ProjetoJPARepository extends CrudRepository<ProjetoJPA, Integer> {


    Optional<ProjetoJPA> findById(int codProjeto);
    Optional<ProjetoJPA> findByCodEstudante (int CodEstudante);

    boolean existsById(int codProjeto);
}