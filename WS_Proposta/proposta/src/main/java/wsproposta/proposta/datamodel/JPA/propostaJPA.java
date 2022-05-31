package wsproposta.proposta.datamodel.JPA;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "propostas")
public class PropostaJPA {

    @Id
    @Getter
    int codProposta;
    @Getter
    private int codUtilizador;
    @Getter
    private int nifOrganizacao;
    @Getter
    private int codEdicao;
    @Getter
    private String titulo;
    @Getter
    private String problema;
    @Getter
    private String objetivo;

    public PropostaJPA() {
    }

    public PropostaJPA(int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) {
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
    }
}
