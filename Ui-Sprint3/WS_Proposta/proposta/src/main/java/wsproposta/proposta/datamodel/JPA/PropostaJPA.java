package wsproposta.proposta.datamodel.JPA;


import lombok.AllArgsConstructor;
import lombok.Getter;
import wsproposta.proposta.domain.entities.Proposta.Estado;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table (name = "propostas")
public class PropostaJPA {

    @Id
    @Getter
    @GeneratedValue(strategy= GenerationType.AUTO)

    private int codProposta;
    @Getter
    private int codUtilizador;
    @Getter
    private long nifOrganizacao;
    @Getter
    private int codEdicao;
    @Getter
    private String titulo;
    @Getter
    private String problema;
    @Getter
    private String objetivo;
    @Getter
    private Estado estado;

    public PropostaJPA() {
    }

    public PropostaJPA(int codUtilizador, long nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo, Estado estado) {
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.estado = estado;
    }
}
