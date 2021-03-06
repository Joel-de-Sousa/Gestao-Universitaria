package Sprint.WsProjeto.datamodel.REST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


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

    public PropostaRestDTO() {
    }

    public PropostaRestDTO(int codProposta, int codUtilizador, long nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo, String estado) {
        this.codProposta = codProposta;
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.estado = estado;
    }
}
