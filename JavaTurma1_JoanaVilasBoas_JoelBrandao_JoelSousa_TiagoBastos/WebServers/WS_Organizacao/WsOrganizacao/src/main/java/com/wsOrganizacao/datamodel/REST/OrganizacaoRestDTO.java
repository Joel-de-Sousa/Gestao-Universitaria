package com.wsOrganizacao.datamodel.REST;

import lombok.Getter;

public class OrganizacaoRestDTO {


    @Getter
    private int nNif;

    @Getter

    private String nDenominacao;

    public OrganizacaoRestDTO(int nNif, String nDenominacao) {
        this.nNif = nNif;
        this.nDenominacao = nDenominacao;
    }
}
