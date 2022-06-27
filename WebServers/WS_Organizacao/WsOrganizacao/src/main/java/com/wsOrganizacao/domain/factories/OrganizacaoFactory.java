package com.wsOrganizacao.domain.factories;

import com.wsOrganizacao.domain.entities.Organizacao;
import org.springframework.stereotype.Service;

@Service
public class OrganizacaoFactory implements IOrganizacaoFactory {

    @Override
    public Organizacao createOrganizacao(int nNif, String sDenominacao) {
        return new Organizacao(nNif,sDenominacao);
    }
}
