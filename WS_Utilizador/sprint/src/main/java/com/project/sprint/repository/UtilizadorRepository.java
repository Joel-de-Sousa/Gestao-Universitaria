package com.project.sprint.repository;

import com.project.sprint.datamodel.UtilizadorJPA;
import com.project.sprint.datamodel.assembler.UtilizadorDomainDataAssembler;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.repository.JPA.UtilizadorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UtilizadorRepository implements IUtilizadorRepository {
    @Autowired
    UtilizadorJpaRepository utilizadorJpaRepository;

    @Autowired
    UtilizadorDomainDataAssembler utilizadorDomainDataAssembler;

    public Utilizador save(Utilizador utilizador){
        UtilizadorJPA utilizadorJPA= utilizadorDomainDataAssembler.toData(utilizador);
        if (!utilizadorJpaRepository.existsByEmail(utilizadorJPA.getEmail())){
        UtilizadorJPA savedUtilizadorJPA=utilizadorJpaRepository.save(utilizadorJPA);
        Utilizador utilizadorSaved=utilizadorDomainDataAssembler.toDomain(savedUtilizadorJPA);
        return utilizadorSaved;}
        else{
            throw new IllegalArgumentException ("O Email já existe na base de dados");
        }
    }

    public Optional<Utilizador> findById(int id) {
        Optional<UtilizadorJPA> opUtilizadorJPA = utilizadorJpaRepository.findById(id);

        if ( opUtilizadorJPA.isPresent() ) {
            Utilizador utilizador = utilizadorDomainDataAssembler.toDomain(opUtilizadorJPA.get());
            return Optional.of( utilizador );
        }
        else
            return Optional.empty();
    }

}
