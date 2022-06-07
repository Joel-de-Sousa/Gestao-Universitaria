package com.wsOrganizacao.datamodel.REST;

import lombok.Getter;
import lombok.Setter;

public class NifRestDTO {

    @Getter
    @Setter
    long nr;
    @Getter
     @Setter
    String name;


    protected NifRestDTO() {
    }

    public NifRestDTO(long nr, String name) {
        this.nr = nr;
        this.name = name;
    }
}
