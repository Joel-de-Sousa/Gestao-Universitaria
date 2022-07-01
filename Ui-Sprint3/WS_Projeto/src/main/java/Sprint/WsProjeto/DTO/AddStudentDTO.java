package Sprint.WsProjeto.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
