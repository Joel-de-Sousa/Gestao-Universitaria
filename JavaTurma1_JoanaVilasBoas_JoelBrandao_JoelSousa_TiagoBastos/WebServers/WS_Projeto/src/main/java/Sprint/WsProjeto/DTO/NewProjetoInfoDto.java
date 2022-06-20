package Sprint.WsProjeto.DTO;

import lombok.Getter;
import lombok.Setter;

public class NewProjetoInfoDto {



    @Getter


    private int codProposta;

    @Getter

    private int codEstudante;

    @Getter

    private int codOrientador;

    public NewProjetoInfoDto() {
    }

    public NewProjetoInfoDto( int codProposta, int codEstudante, int codOrientador) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
    }

    public NewProjetoInfoDto(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }
}
