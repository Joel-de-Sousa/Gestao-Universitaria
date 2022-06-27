package com.wsOrganizacao.DTO.assemblers;

import com.wsOrganizacao.DTO.OrganizacaoDTO;
import org.springframework.stereotype.Service;

@Service
public class OrganizacaoDomainDTOAssembler {

    public OrganizacaoDomainDTOAssembler() {
    }

    public OrganizacaoDTO toDTO (int nNif, String sDenominacao){

        return new OrganizacaoDTO(nNif, sDenominacao);
    }

}
