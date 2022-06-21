package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AddStudentDTO {

    int codEdicao;
    int codEstudante;

    public AddStudentDTO(int codEstudante) {
        this.codEstudante = codEstudante;
    }
}
