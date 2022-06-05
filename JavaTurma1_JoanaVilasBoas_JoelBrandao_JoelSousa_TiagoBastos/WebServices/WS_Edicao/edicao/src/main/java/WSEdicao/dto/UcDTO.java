package WSEdicao.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode
@ToString
@Getter
public class UcDTO{

    int codUc;
    String sigla;
    String denominacao;

    public UcDTO() {
    }

    public UcDTO(int codUc,String sSigla, String sDenominacao) {
        this.codUc=codUc;
        this.sigla = sSigla;
        this.denominacao = sDenominacao;
    }
}
