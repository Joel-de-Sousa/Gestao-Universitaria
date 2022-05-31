package WSEdicao.dto;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class EdicaoDTO {

    int codEdicao;
    Uc codUc;
    AnoLetivo codAnoLetivo;

    public EdicaoDTO() {
    }
}
