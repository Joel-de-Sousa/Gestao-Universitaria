package wsproposta.proposta.domain.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wsproposta.proposta.utils.Util;

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
    long nifOrganizacao;

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


    public Proposta(int codUtilizador, long nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo/*, Estado estado*/) {

        this.codUtilizador = codUtilizador;

        this.nifOrganizacao = nifOrganizacao;

        this.codEdicao = codEdicao;

        if(Util.validaStringMinCarateresNaoBrancos(10, sTitulo))
            this.titulo = sTitulo;
        else
            throw new IllegalArgumentException ("O valor do parâmetro 'Título' deve ter no mínimo 10 caracteres não brancos");

        if(Util.validaStringMinCarateresNaoBrancos(10, sProblema))
            this.problema = sProblema;
        else
            throw new IllegalArgumentException ("O valor do parâmetro 'Problema' deve ter no mínimo 10 caracteres não brancos");

        if(Util.validaStringMinCarateresNaoBrancos(10, sObjetivo))
            this.objetivo = sObjetivo;
        else
            throw new IllegalArgumentException ("O valor do parâmetro 'Objectivo' deve ter no mínimo 10 caracteres não brancos");

        this.estado = ESTADO_POR_OMISSAO;

    }
}
