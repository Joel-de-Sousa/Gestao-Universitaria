package WSEdicao.domain.entities;

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
        this.denominacao = denominacao;
        this.codEdicao = codEdicao;
    }

    public MomentoAvaliacao(int codMomentoAvaliacao) {
        this.codMomentoAvaliacao = codMomentoAvaliacao;
    }
}
