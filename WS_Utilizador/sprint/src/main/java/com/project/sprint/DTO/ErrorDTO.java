package com.project.sprint.DTO;

import lombok.Getter;
import lombok.Setter;


public class ErrorDTO {


    @Getter @Setter
    String errorMessage;

    public ErrorDTO() {

    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}