package com.wsOrganizacao.repositories.IRepository;

import com.wsOrganizacao.domain.entities.Organizacao;

import java.util.Optional;

public interface IOrganizacaoRepository {
     Organizacao save(Organizacao organizacao);

     Optional<Organizacao> findByNif(int nNif);
}
