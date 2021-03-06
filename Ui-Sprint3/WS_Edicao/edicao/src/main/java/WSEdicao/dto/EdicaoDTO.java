package WSEdicao.dto;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class EdicaoDTO {

    int codEdicao;
    int codUc;
    int codAnoLetivo;
    int codRUC;
    String estado;

    public EdicaoDTO() {
    }

}
