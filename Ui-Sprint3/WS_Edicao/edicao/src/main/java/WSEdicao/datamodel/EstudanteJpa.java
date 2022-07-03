package WSEdicao.datamodel;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
public class EstudanteJpa {

    private int codEdicao;
    @Id
    private int codEstudante;

    public EstudanteJpa(int codEstudante) {
        this.codEstudante = codEstudante;
    }
}
