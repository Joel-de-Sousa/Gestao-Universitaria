package WSEdicao.domain.entities;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
public class AnoLetivo {

    private int codAnoLetivo;
    private String ano;

    public AnoLetivo(String ano) {
        this.ano = setValidAno(ano);
    }

    private String setValidAno(String ano) {
        if (ano == null || ano.isEmpty())
            throw new IllegalArgumentException("Ano Inválido, preencha o Ano com o seguinte formato: AAAA");
        else {
            /*if (ano.length() == 8) {
                ano = addHyphenToAno(ano);
                return ano;
            }*/
            if (anoIsANumber(ano)) {
                return anoIsInCorrectFormat(ano);
            } else {
                throw new IllegalArgumentException("Ano Inválido, preencha o Ano com o seguinte formato: AAAA");
            }
        }
    }

    private boolean anoIsANumber(String ano) {
        String regex = "^[0-9]{4}$";
        //String regex = "^[0-9]{4}-[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ano);
        return matcher.matches();
    }

    /*private static String addHyphenToAno(String ano) {
        return ano.substring(0, 4) + "-" + ano.substring(4, ano.length());
    }*/

    private String anoIsInCorrectFormat(String ano){
        int aux = Integer.parseInt(ano);
        if(aux>1949 && aux<2050) {
            String correctFormat = String.format(ano + "-" + (Math.addExact(aux,1)));
           return correctFormat;
        } else
            throw new IllegalArgumentException("Porfavor escreva um ano válido");
    }

    public AnoLetivo(int codAnoLetivo) {
        this.codAnoLetivo = codAnoLetivo;
    }
}

