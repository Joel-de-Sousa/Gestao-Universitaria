package com.wsOrganizacao.datamodel.REST.assembler;

import com.wsOrganizacao.datamodel.REST.OrganizacaoRestDTO;
import com.wsOrganizacao.domain.entities.Organizacao;
import org.springframework.stereotype.Service;

@Service
public class OrganizacaoDomainDataRestAssembler {

    public OrganizacaoRestDTO toData (Organizacao organizacao){

        return new OrganizacaoRestDTO(organizacao.getNif(),organizacao.getDenominacao());
    }

    public Organizacao toDomain(OrganizacaoRestDTO organizacaoRestDTO){

        return new Organizacao(organizacaoRestDTO.getNNif(),organizacaoRestDTO.getNDenominacao());
    }
}
