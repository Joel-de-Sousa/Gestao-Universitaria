package WSEdicao.domain.entities;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
public class AnoLetivo {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codAnoLetivo;
    private String ano;

    public AnoLetivo(String ano) {
        this.ano = setValidAno(ano);
    }

    private String setValidAno(String ano) {
        if (ano == null || ano.isEmpty())
            throw new IllegalArgumentException("Ano Inválido, preencha com o seguinte formato: AAAAinicial-AAAAseguinte");
        else {
            if (ano.length() == 8) {
                ano = addHyphenToAno(ano);
                return ano;
            }
            if (anoIsInCorrectFormat(ano)) {
                return ano;
            } else {
                throw new IllegalArgumentException("Ano Inválido, preencha com o seguinte formato: AAAAinicial-AAAAseguinte");
            }
        }
    }

    private boolean anoIsInCorrectFormat(String ano) {
        String regex = "^[0-9]{4}-[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ano);
        return matcher.matches();
    }

    private static String addHyphenToAno(String ano) {
        return ano.substring(0, 4) + "-" + ano.substring(4, ano.length());
    }
}

