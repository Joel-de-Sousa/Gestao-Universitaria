package wsproposta.proposta.DTO;

import lombok.Getter;

public class NewPropostaInfoDTO {

    @Getter
    int codProposta;
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

    public NewPropostaInfoDTO() {
    }

    public NewPropostaInfoDTO(int codProposta, int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) {
        this.codProposta = codProposta;
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
    }
}