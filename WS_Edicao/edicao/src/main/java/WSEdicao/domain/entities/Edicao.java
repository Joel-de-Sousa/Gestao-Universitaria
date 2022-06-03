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

    private int codEdicao;
    private Uc uc;
    private AnoLetivo anoLetivo;

    public Edicao(Uc codUc, AnoLetivo anoLetivo) {
        this.uc = codUc;
        this.anoLetivo = anoLetivo;
    }

    public Edicao(int codEdicao) {
        this.codEdicao = codEdicao;
    }
}
