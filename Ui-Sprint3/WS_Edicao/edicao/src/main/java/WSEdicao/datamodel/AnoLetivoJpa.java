package WSEdicao.datamodel;

import lombok.Getter;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Entity
@Table(name="anosletivos")
public class AnoLetivoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codAnoLetivo_seq")
    private int codAnoLetivo;
    private String ano;


    protected AnoLetivoJpa(){}

    public AnoLetivoJpa(String ano) {
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
