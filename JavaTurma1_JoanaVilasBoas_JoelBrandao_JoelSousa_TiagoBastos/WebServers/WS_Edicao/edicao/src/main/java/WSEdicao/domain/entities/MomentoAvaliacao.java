package WSEdicao.domain.entities;

import WSEdicao.utils.Util;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MomentoAvaliacao {


    private int codMomentoAvaliacao;
    private String denominacao;

    public MomentoAvaliacao(String denominacao) {
        if (Util.validaStringMinCarateresNaoBrancos(3, denominacao)) {
            this.denominacao = denominacao;
        } else
            throw new IllegalArgumentException("A denominção deve contem no mínimo 3 caracteres não brancos");
    }

    public MomentoAvaliacao(int codMomentoAvaliacao) {
        this.codMomentoAvaliacao = codMomentoAvaliacao;
    }
}
