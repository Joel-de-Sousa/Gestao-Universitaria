package WSEdicao.domain.entities;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Edicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codEdicao;

    private Uc codUc;
    private AnoLetivo anoLetivo;

    public Edicao(Uc codUc, AnoLetivo anoLetivo) {
        this.codUc = codUc;
        this.anoLetivo = anoLetivo;
    }
}
