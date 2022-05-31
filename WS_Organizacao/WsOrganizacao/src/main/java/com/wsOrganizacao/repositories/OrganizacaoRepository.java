package com.wsOrganizacao.repositories;

import com.wsOrganizacao.datamodel.JPA.OrganizacaoJPA;
import com.wsOrganizacao.datamodel.JPA.assembler.OrganizacaoDomainDataAssembler;
import com.wsOrganizacao.domain.entities.Organizacao;
import com.wsOrganizacao.repositories.IRepository.IOrganizacaoJPaRepository;
import com.wsOrganizacao.repositories.IRepository.IOrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrganizacaoRepository implements IOrganizacaoRepository {

    @Autowired
    IOrganizacaoJPaRepository iOrganizacaoJpaRepository;

    @Autowired
    OrganizacaoDomainDataAssembler organizacaoDomainDataAssembler;


    public Organizacao save(Organizacao organizacao){

        OrganizacaoJPA organizacaoJPA = organizacaoDomainDataAssembler.toData(organizacao);

        OrganizacaoJPA savedOrganizacaoJPA = iOrganizacaoJpaRepository.save(organizacaoJPA);

        return organizacaoDomainDataAssembler.toDomain(savedOrganizacaoJPA);
    }



    public Optional<Organizacao> findByNif(int nNif) {
        Optional<OrganizacaoJPA> opOrganizacao = iOrganizacaoJpaRepository.findByNif(nNif);

        if ( opOrganizacao.isPresent() ) {
            Organizacao organizacao = organizacaoDomainDataAssembler.toDomain(opOrganizacao.get());
            return Optional.of( organizacao );
        }
        else
          return Optional.empty();
    }
}
