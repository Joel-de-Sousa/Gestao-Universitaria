package com.project.sprint.repository;

import com.project.sprint.datamodel.UtilizadorJPA;
import com.project.sprint.datamodel.assembler.UtilizadorDomainDataAssembler;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.repository.JPA.UtilizadorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UtilizadorRepository implements IUtilizadorRepository {
    @Autowired
    UtilizadorJpaRepository utilizadorJpaRepository;

    @Autowired
    UtilizadorDomainDataAssembler utilizadorDomainDataAssembler;

    /**
     * Método para gravar um Utilizador na Bd.
     * Faz se uma verificação por o email e caso ele nao exista,
     * o Utilizador é convertido em Jpa no assembler, gravado como UtilizadorJPA e convertido de volta para o domain.
     * @param utilizador objeto do tipo Utilizador
     * @return um Objeto do tipo UtilizadorJPA em caso de sucesso ou uma exceção com a mensagem "O Email já existe na base de dados" no caso de email já existir na BD.
     */
    public Utilizador save(Utilizador utilizador) {

        UtilizadorJPA utilizadorJPA = utilizadorDomainDataAssembler.toData(utilizador);

        if (!utilizadorJpaRepository.existsByEmail(utilizadorJPA.getEmail())) {
            UtilizadorJPA savedUtilizadorJPA = utilizadorJpaRepository.save(utilizadorJPA);
            Utilizador utilizadorSaved = utilizadorDomainDataAssembler.toDomain(savedUtilizadorJPA);
            return utilizadorSaved;
        } else {
            throw new IllegalArgumentException("O Email já existe na base de dados");
        }
    }

    /**
     *  Método que comunica com Crud para encontrar um Utilizador na Bd através do método findById do Crud.
     *
     * @param id a identificação do Utilizador
     * @return um Optional de Utilizador em caso de sucesso ou um Optional.empty no caso de não serem encontrados resultados.
     */
    public Optional<Utilizador> findById(int id) {
        Optional<UtilizadorJPA> opUtilizadorJPA = utilizadorJpaRepository.findById(id);

        if (opUtilizadorJPA.isPresent()) {
            Utilizador utilizador = utilizadorDomainDataAssembler.toDomain(opUtilizadorJPA.get());
            return Optional.of(utilizador);
        } else
            return Optional.empty();
    }

    public List<Utilizador> findAllByTipoUtilizador(String tipoUtilizador) {

        List<UtilizadorJPA> listUtilizadoresJPA = utilizadorJpaRepository.findAllByTipoUtilizador(Utilizador.TipoUtilizador.valueOf(tipoUtilizador));
        List<Utilizador> listUtilizadores =new ArrayList<>();
        for (UtilizadorJPA l : listUtilizadoresJPA) {
            listUtilizadores.add(utilizadorDomainDataAssembler.toDomain(l));
        }
        return listUtilizadores;

    }
}
