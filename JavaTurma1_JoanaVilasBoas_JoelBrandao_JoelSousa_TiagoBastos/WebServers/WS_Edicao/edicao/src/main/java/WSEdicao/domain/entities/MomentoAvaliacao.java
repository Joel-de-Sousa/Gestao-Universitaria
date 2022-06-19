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
    private int codEdicao;
    private String denominacao;

    public MomentoAvaliacao(int codEdicao,String denominacao) {
        if (Util.validaStringMinCarateresNaoBrancos(3, denominacao)) {
            this.denominacao = denominacao;
        } else
            throw new IllegalArgumentException("A denominção deve contem no mínimo 3 caracteres não brancos");
        this.codEdicao = codEdicao;
    }

    public MomentoAvaliacao(int codMomentoAvaliacao) {
        this.codMomentoAvaliacao = codMomentoAvaliacao;
    }
}
