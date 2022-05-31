package com.wsOrganizacao.domain.factories;

import com.wsOrganizacao.domain.entities.Organizacao;

public interface IOrganizacaoFactory {

    Organizacao createOrganizacao ( int nNif, String sDenominacao);
}
