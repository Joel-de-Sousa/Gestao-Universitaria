package WSEdicao.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EdicaoAllArgsDTO {

    int codEdicao;
    int codUc;
    String sigla;
    String denominacao;
    int codAnoLetivo;
    String ano;
    int codRUC;

}
