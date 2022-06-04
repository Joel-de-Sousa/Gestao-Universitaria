package WSEdicao.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EdicaoAllArgsDTO {

    int edicao;
    int codUc;
    String sigla;
    String denominacao;
    int codAnoLetivo;
    String ano;

}
