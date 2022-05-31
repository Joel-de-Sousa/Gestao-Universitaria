package com.wsOrganizacao.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class OrganizacaoDTO {


    @Getter
    private int nNif;

    @Getter
    private String sDenominacao;

}
