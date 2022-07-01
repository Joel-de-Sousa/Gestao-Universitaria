package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PropostaRestDTO {

    @Getter
    int codProposta;
    @Getter
    int codUtilizador;
    @Getter
    long nifOrganizacao;
    @Getter
    int codEdicao;
    @Getter
    String titulo;
    @Getter
    String problema;
    @Getter
    String objetivo;
    @Getter
    String estado;

    public PropostaRestDTO(int codUtilizador, long nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) {
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
    }

    public PropostaRestDTO(long nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo, String estado) {
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.estado = estado;
    }

    public PropostaRestDTO(int codProposta, String estado) {
        this.codProposta = codProposta;
        this.estado = estado;
    }

    public PropostaRestDTO(int codProposta, String titulo, String estado) {
        this.codProposta = codProposta;
        this.titulo = titulo;
        this.estado = estado;
    }
}
