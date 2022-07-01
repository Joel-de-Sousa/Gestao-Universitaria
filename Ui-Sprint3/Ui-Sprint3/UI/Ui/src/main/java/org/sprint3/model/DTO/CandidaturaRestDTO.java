package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CandidaturaRestDTO {


    @Getter
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    String estadoEstudante;

    public CandidaturaRestDTO(int codCandidatura, int codProposta, int codEstudante, String estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.estadoEstudante = estadoEstudante;
    }

    public CandidaturaRestDTO(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }

    public CandidaturaRestDTO(int codCandidatura, int codProposta, String estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.codProposta = codProposta;
        this.estadoEstudante = estadoEstudante;
    }

    public CandidaturaRestDTO(int codCandidatura, String estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.estadoEstudante = estadoEstudante;
    }

    /*@Getter
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    String tituloProposta;
    @Getter
    String problemaProposta;
    @Getter
    String objectivoProposta;
   *//* @Getter
    String edicaoProposta;*//*
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
*/

}
