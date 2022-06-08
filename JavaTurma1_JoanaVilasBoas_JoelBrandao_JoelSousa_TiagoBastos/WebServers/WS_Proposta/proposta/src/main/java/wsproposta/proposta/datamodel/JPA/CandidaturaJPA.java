package wsproposta.proposta.datamodel.JPA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wsproposta.proposta.domain.entities.Candidatura;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "candidaturas")
public class CandidaturaJPA {

    @Id
    @Getter
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    int codOrientador;
    @Getter
    Candidatura.Estado estadoEstudante;
    @Getter
    Candidatura.Estado estadoOrientador;

    public CandidaturaJPA() {
    }

    public CandidaturaJPA(int codProposta, int codEstudante, int codOrientador, Candidatura.Estado estadoEstudante, Candidatura.Estado estadoOrientador) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.estadoEstudante = estadoEstudante;
        this.estadoOrientador = estadoOrientador;
    }
}
