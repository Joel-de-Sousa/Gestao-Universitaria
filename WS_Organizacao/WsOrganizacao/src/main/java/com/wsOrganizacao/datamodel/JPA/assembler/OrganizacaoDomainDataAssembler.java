package com.wsOrganizacao.datamodel.JPA.assembler;

import com.wsOrganizacao.datamodel.JPA.OrganizacaoJPA;
import com.wsOrganizacao.domain.entities.Organizacao;
import org.springframework.stereotype.Service;

@Service
public class OrganizacaoDomainDataAssembler {


    public OrganizacaoJPA toData (Organizacao organizacao){

        return new OrganizacaoJPA(organizacao.getNNif(),organizacao.getSDenominacao());
    }

    public Organizacao toDomain (OrganizacaoJPA organizacaoJPA){

        return new Organizacao(organizacaoJPA.getNif(), organizacaoJPA.getSDenominacao());
    }
}
