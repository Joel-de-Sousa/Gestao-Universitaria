package WSEdicao.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Edicao {

    @Id
    private int codEdicao;
    private Uc codUc;
    private AnoLetivo anoLetivo;

    public Edicao(int codEdicao, Uc codUc, AnoLetivo anoLetivo) {
        this.codEdicao = codEdicao;
        this.codUc = codUc;
        this.anoLetivo = anoLetivo;
    }
}
