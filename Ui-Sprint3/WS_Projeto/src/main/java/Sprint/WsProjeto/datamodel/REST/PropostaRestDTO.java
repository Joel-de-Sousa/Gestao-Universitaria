package Sprint.WsProjeto.datamodel.REST;

import lombok.Getter;
import lombok.Setter;

public class PropostaRestDTO {


    @Setter
    @Getter
    int codProposta;

    @Getter
    @Setter
    int codUtilizador;
    @Getter
    @Setter
    int nifOrganizacao;
    @Getter
    @Setter
    int codEdicao;
    @Getter
    @Setter
    String titulo;
    @Getter
    @Setter
    String problema;
    @Getter
    @Setter
    String objetivo;
    @Getter
    @Setter
    String estado;

    public PropostaRestDTO(int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo, String estado) {
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.estado = estado;
    }
}
