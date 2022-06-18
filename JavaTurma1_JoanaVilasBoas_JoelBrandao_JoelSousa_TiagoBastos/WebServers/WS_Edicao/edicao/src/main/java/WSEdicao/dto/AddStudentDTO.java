package WSEdicao.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class AddStudentDTO {

        int codEdicao;
        int codEstudante;

        public AddStudentDTO(int codEstudante) {
                this.codEstudante = codEstudante;
        }
}
