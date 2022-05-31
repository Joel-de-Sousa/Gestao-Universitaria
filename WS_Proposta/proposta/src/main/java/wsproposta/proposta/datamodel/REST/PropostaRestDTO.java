package wsproposta.proposta.datamodel.REST;

import lombok.Getter;

public class PropostaRestDTO {

    @Getter
    int codUtilizador;
    @Getter
    int nifOrganizacao;
    @Getter
    int codEdicao;
    @Getter
    String titulo;
    @Getter
    String problema;
    @Getter
    String objetivo;

    public PropostaRestDTO() {
    }

    public PropostaRestDTO( int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) {
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
    }
}
