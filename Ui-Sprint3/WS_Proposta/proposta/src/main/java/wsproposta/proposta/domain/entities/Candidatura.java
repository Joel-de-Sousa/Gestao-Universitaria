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


    @Getter @Setter
    int codCandidatura;

    @Getter @Setter
    int codProposta;

    @Getter @Setter
    int codEstudante;

    @Getter @Setter
    Estado estadoEstudante;

    private static final Candidatura.Estado ESTADO_ESTUDANTE_POR_OMISSAO = Candidatura.Estado.PENDENTE;

    public Candidatura(int codCandidatura, int codProposta, int codEstudante, Estado estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.estadoEstudante = estadoEstudante;
    }

    public Candidatura(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.estadoEstudante = ESTADO_ESTUDANTE_POR_OMISSAO;
    }

    /*public Candidatura(int codCandidatura, Estado estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.estadoEstudante = estadoEstudante;
    }*/

   /* public Candidatura(int codCandidatura, Estado estadoOrientador) {
        this.codCandidatura = codCandidatura;
        this.estadoOrientador = estadoOrientador;
    }*/
}
