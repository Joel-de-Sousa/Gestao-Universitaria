package WSEdicao.dto;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class NewEdicaoInfoDTO {

    int codEdicao;
    int codUc;
    int codAnoLetivo;
    int codRUC;

    public NewEdicaoInfoDTO() {
    }
}
