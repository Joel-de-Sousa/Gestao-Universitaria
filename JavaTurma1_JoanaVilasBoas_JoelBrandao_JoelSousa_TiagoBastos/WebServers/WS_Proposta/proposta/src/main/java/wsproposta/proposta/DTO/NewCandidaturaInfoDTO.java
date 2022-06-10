package wsproposta.proposta.DTO;

import lombok.Getter;

public class NewCandidaturaInfoDTO {

    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    String estadoEstudante;

    public NewCandidaturaInfoDTO() {
    }

    public NewCandidaturaInfoDTO(int codProposta, int codEstudante, String estadoEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.estadoEstudante = estadoEstudante;
    }
}
