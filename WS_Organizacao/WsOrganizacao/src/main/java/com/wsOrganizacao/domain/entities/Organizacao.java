package com.wsOrganizacao.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@EqualsAndHashCode
@ToString

public class Organizacao {



    @Getter
    @Setter
    @Id
    private int nNif;

    @Getter
    @Setter
    private String sDenominacao;


    public Organizacao(int nNif, String sDenominacao) {
        this.nNif = nNif;
        this.sDenominacao = sDenominacao;
    }
}
