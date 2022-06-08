package com.project.sprint.repository;

import com.project.sprint.domain.entities.Utilizador;

import java.util.Optional;

public interface IUtilizadorRepository {

    public Utilizador save(Utilizador utilizador );
    public Optional<Utilizador> findById(int codUtilizador );
}