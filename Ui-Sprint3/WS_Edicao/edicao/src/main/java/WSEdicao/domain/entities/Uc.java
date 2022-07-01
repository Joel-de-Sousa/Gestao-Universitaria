package WSEdicao.domain.entities;

import WSEdicao.utils.Util;
import lombok.*;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Uc {
    
    private int codUc;
    private String sigla;
    private String denominacao;

    public Uc(String sSigla, String sDenominacao) {
        if (Util.validaStringComMinSemAlgSemSimbolo(3, 10, sSigla)) {
            this.sigla = sSigla;
        } else
            throw new IllegalArgumentException("A Sigla deve conter no mínimo 3 carateres, sem espaços, sem símbolos e sem algarismos");

        if (Util.validaStringMinCarateresNaoBrancos(10, sDenominacao)) {
            this.denominacao = sDenominacao;
        } else
            throw new IllegalArgumentException("A denominção deve contem no mínimo 10 caracteres não brancos");
    }

    public Uc(int codUc) {
        this.codUc = codUc;
    }
}

