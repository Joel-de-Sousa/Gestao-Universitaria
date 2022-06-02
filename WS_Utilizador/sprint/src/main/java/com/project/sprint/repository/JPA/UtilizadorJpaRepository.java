package com.project.sprint.repository.JPA;

import com.project.sprint.datamodel.UtilizadorJPA;
import com.project.sprint.domain.entities.Utilizador;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilizadorJpaRepository extends CrudRepository<UtilizadorJPA,Integer> {
    Optional<UtilizadorJPA> findById(int codUtilizador);
    boolean existsByEmail(String email);

}
