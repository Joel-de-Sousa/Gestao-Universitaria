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
    private int nif;

    @Getter
    @Setter
    private String denominacao;


    public Organizacao(int nNif, String sDenominacao) {
        this.nif = nNif;
        this.denominacao = sDenominacao;
    }
}
