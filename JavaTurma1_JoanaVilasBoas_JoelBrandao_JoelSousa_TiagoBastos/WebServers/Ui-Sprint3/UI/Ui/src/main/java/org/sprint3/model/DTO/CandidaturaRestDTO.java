package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CandidaturaRestDTO {

    @Getter
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    String tituloProposta;
    @Getter
    String problemaProposta;
    @Getter
    String objectivoProposta;
    @Getter
    String edicaoProposta;
    @Getter
    int codEstudante;
    @Getter
    String nomeEstudante;
    @Getter
    String sobrenomeEstudante;
    @Getter
    String estado;

    public CandidaturaRestDTO(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }

    public CandidaturaRestDTO(int codCandidatura, String estado) {
        this.codCandidatura = codCandidatura;
        this.estado = estado;
    }

    public CandidaturaRestDTO(int codCandidatura, String tituloProposta, String nomeEstudante, String sobrenomeEstudante, String estado) {
        this.codCandidatura = codCandidatura;
        this.tituloProposta = tituloProposta;
        this.nomeEstudante = nomeEstudante;
        this.sobrenomeEstudante = sobrenomeEstudante;
        this.estado = estado;
    }
}
