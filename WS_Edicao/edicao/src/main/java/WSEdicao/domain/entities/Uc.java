package WSEdicao.domain.entities;

import WSEdicao.utils.Util;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Setter
@Getter
public class Uc {

    private int codUc;
    private String sSigla;
    private String sDenominacao;

    public Uc(String sSigla, String sDenominacao) {
        if(Util.validaStringComMinSemAlgSemSimbolo(3,10,sSigla)){
            this.sSigla = sSigla;
        } else
            throw new IllegalArgumentException("A Sigla deve conter no mínimo 3 carateres, sem espaços, sem símbolos e sem algarismos");
        this.sDenominacao = sDenominacao;
    }


}
