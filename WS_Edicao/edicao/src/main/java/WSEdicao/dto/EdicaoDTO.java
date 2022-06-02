package WSEdicao.dto;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class EdicaoDTO {

    int codEdicao;
    int codUc;
    int codAnoLetivo;

    public EdicaoDTO() {
    }

    public EdicaoDTO(int codUc, int codAnoLetivo) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
    }
}
