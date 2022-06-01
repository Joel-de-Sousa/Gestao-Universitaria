package wsproposta.proposta.domain.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@EqualsAndHashCode
public class Proposta {

    public enum Estado {PENDENTE, ACEITE, REJEITADA};

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Getter @Setter
    int codProposta;

    @Getter @Setter
    int codUtilizador;

    @Getter @Setter
    int nifOrganizacao;

    @Getter @Setter
    int codEdicao;

    @Getter @Setter
    String titulo;

    @Getter @Setter
    String problema;

    @Getter @Setter
    String objetivo;

    @Getter @Setter
    Estado estado;

    private static final Estado ESTADO_POR_OMISSAO = Estado.PENDENTE;


    public Proposta(int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo/*, Estado estado*/) {

        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = sTitulo;
        this.problema = sProblema;
        this.objetivo = sObjetivo;
        this.estado = ESTADO_POR_OMISSAO;

    }
}
