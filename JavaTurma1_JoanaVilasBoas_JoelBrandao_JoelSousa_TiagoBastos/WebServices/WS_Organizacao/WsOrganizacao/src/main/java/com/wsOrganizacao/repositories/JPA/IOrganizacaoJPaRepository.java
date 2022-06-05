package com.wsOrganizacao.repositories.JPA;

import com.wsOrganizacao.datamodel.JPA.OrganizacaoJPA;
import com.wsOrganizacao.domain.entities.Organizacao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IOrganizacaoJPaRepository extends CrudRepository<OrganizacaoJPA, Integer> {


    Optional<OrganizacaoJPA> findByNif(int nNif);


}