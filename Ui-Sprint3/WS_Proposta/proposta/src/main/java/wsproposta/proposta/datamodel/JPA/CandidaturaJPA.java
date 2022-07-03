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
    @GeneratedValue(strategy= GenerationType.IDENTITY/*generator = "codCandidatura"*/)
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    Candidatura.Estado estadoEstudante;

    public CandidaturaJPA() {
    }

    public CandidaturaJPA(int codProposta, int codEstudante, Candidatura.Estado estadoEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.estadoEstudante = estadoEstudante;
    }
}
