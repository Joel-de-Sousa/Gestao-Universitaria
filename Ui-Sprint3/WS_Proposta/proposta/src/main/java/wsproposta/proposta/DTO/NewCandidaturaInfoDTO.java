package wsproposta.proposta.DTO;

import lombok.Getter;

public class NewCandidaturaInfoDTO {

    @Getter
    int codProposta;
    @Getter
    int codEstudante;


    public NewCandidaturaInfoDTO() {
    }

    public NewCandidaturaInfoDTO(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }
}
