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

        int codEstudante;
        int codUtilizador;
        int codEdicao;

        public AddStudentDTO(int codUtilizador, int codEdicao) {
                this.codUtilizador = codUtilizador;
                this.codEdicao = codEdicao;
        }
}
