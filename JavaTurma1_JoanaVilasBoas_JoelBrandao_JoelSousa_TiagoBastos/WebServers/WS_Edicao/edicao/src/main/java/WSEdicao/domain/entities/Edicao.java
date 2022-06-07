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
    private int uc;
    private int anoLetivo;

    public Edicao(int codUc, int anoLetivo) {
        this.uc = codUc;
        this.anoLetivo = anoLetivo;
    }

    public Edicao(int codEdicao) {
        this.codEdicao = codEdicao;
    }
}
