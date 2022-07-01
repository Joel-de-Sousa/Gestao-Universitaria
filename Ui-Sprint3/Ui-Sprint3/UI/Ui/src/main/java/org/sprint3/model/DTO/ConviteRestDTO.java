package org.sprint3.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ConviteRestDTO {

    @Getter
    int codConvite;
    @Getter
    int codProjeto;
    @Getter
    int codEstudante;
    @Getter
    int codDocente;
    @Getter
    String estado;

    public ConviteRestDTO(int codConvite, int codProjeto, int codEstudante, int codDocente, String estado) {
        this.codConvite = codConvite;
        this.codProjeto = codProjeto;
        this.codEstudante = codEstudante;
        this.codDocente = codDocente;
        this.estado = estado;
    }

    public ConviteRestDTO(int codProjeto, int codEstudante, int codDocente) {
        this.codProjeto = codProjeto;
        this.codEstudante = codEstudante;
        this.codDocente = codDocente;
    }

    public ConviteRestDTO(int codConvite, String estado) {
        this.codConvite = codConvite;
        this.estado = estado;
    }

    /* @Getter
    int codConvite;
    @Getter
    int codEstudante;
    @Getter
    String nomeEstudante;
    @Getter
    String sobrenomeEstudante;
    @Getter
    int codDocente;
    @Getter
    int codProjeto;
    @Getter
    String tituloProjeto;
    @Getter
    String problemaProjeto;
    @Getter
    String objectivoProjeto;
    @Getter
    String edicao;
    @Getter
    String estado;

    public ConviteRestDTO(int codEstudante, int codDocente, int codProjeto) {
        this.codEstudante = codEstudante;
        this.codDocente = codDocente;
        this.codProjeto = codProjeto;
    }

    public ConviteRestDTO(int codConvite, String nomeEstudante, String sobrenomeEstudante, String tituloProjeto) {
        this.codConvite = codConvite;
        this.nomeEstudante = nomeEstudante;
        this.sobrenomeEstudante = sobrenomeEstudante;
        this.tituloProjeto = tituloProjeto;
    }

    public ConviteRestDTO(int codConvite, String estado) {
        this.codConvite = codConvite;
        this.estado = estado;
    }*/
}
