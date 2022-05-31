package WSEdicao.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class UcDTO{

    int codUc;
    String sSigla;
    String sDenominacao;

    public UcDTO() {
    }
}
