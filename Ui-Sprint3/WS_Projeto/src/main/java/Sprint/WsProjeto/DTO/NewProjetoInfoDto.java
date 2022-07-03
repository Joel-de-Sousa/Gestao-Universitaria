package Sprint.WsProjeto.DTO;

import lombok.Getter;
import lombok.Setter;

public class NewProjetoInfoDto {



    @Getter
    private int codProposta;

    @Getter
    private int codEstudante;


    public NewProjetoInfoDto() {
    }


    public NewProjetoInfoDto(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }
}
