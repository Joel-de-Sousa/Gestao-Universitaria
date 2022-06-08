package wsproposta.proposta.DTO;

import lombok.Getter;

public class NewCandidaturaInfoDTO {

    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    int codOrientador;
    @Getter
    String estadoEstudante;
    @Getter
    String estadoOrientador;

    public NewCandidaturaInfoDTO() {
    }

    public NewCandidaturaInfoDTO(int codProposta, int codEstudante, int codOrientador, String estadoEstudante, String estadoOrientador) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.estadoEstudante = estadoEstudante;
        this.estadoOrientador = estadoOrientador;
    }
}
