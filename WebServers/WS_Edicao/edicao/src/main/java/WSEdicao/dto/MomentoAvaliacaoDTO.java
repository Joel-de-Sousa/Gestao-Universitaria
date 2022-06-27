package WSEdicao.dto;

import lombok.*;


@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class MomentoAvaliacaoDTO {

    int codMomentoAvaliacao;
    String denominacao;

    public MomentoAvaliacaoDTO() {
    }

    public MomentoAvaliacaoDTO(String denominacao) {
        this.denominacao = denominacao;
    }
}
