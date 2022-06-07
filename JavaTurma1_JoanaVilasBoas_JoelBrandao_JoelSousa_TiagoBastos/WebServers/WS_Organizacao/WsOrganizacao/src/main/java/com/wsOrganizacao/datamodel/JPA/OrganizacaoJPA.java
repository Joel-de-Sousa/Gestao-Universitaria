package com.wsOrganizacao.datamodel.JPA;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organizacoes")
public class OrganizacaoJPA {


    @Id
    @Getter
    private int nif;

    @Getter
    private String denominacao;

    public OrganizacaoJPA() {
    }

    public OrganizacaoJPA(int nNif, String sDenominacao) {
        this.nif = nNif;
        this.denominacao = sDenominacao;
    }
}
