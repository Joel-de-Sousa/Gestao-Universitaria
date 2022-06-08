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
public class Candidatura {


    public enum Estado {PENDENTE, ACEITE, REJEITADA};

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Getter @Setter
    int codCandidatura;

    @Getter @Setter
    int codProposta;

    @Getter @Setter
    int codEstudante;

    @Getter @Setter
    int codOrientador;

    @Getter @Setter
    Estado estadoEstudante;

    @Getter @Setter
    Estado estadoOrientador;

    private static final Candidatura.Estado ESTADO_ESTUDANTE_POR_OMISSAO = Candidatura.Estado.PENDENTE;
    private static final Candidatura.Estado ESTADO_ORIENTADOR_POR_OMISSAO = Candidatura.Estado.PENDENTE;
    private static final int COD_ORIENTADOR_POR_OMISSAO = 0;

    public Candidatura(int codCandidatura, int codProposta, int codEstudante, int codOrientador, Estado estadoEstudante, Estado estadoOrientador) {
        this.codCandidatura = codCandidatura;
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.estadoEstudante = estadoEstudante;
        this.estadoOrientador = estadoOrientador;
    }

    public Candidatura(int codProposta, int codEstudante, int codOrientador, Estado estadoEstudante, Estado estadoOrientador) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = COD_ORIENTADOR_POR_OMISSAO;
        this.estadoEstudante = ESTADO_ESTUDANTE_POR_OMISSAO;
        this.estadoOrientador = ESTADO_ORIENTADOR_POR_OMISSAO;
    }

    public Candidatura(int codCandidatura, Estado estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.estadoEstudante = estadoEstudante;
    }

   /* public Candidatura(int codCandidatura, Estado estadoOrientador) {
        this.codCandidatura = codCandidatura;
        this.estadoOrientador = estadoOrientador;
    }*/
}
