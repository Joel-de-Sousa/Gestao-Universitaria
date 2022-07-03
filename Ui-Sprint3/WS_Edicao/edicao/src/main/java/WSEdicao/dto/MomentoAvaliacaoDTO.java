package WSEdicao.dto;

import lombok.*;


@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class MomentoAvaliacaoDTO {

    int codMomentoAvaliacao;
    int codEdicao;
    String denominacao;

    public MomentoAvaliacaoDTO() {
    }

    public MomentoAvaliacaoDTO(int codEdicao, String denominacao) {
        this.codEdicao = codEdicao;
        this.denominacao = denominacao;
    }
}
