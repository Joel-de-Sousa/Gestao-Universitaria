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
    private int Nif;

    @Getter
    private String sDenominacao;

    public OrganizacaoJPA() {
    }

    public OrganizacaoJPA(int nNif, String sDenominacao) {
        this.Nif = nNif;
        this.sDenominacao = sDenominacao;
    }
}
