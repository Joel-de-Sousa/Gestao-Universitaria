package com.wsOrganizacao.datamodel.REST;

import lombok.Getter;

public class NifRestDTO {

    @Getter
    private int nNif;

    protected NifRestDTO() {
    }

    public NifRestDTO(int nNif) {
        this.nNif = nNif;
    }
}
