package WSEdicao.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Uc {

    @Id
    private int codUc;

    private String sSigla;
    private String sDenominacao;

    public Uc(int codUc, String sSigla, String sDenominacao) {
        this.codUc = codUc;

        if (sSigla != null && (sSigla.length() > 3 && sSigla.length() < 100)) {
            char c;
            for (int i = 0; i < sSigla.length(); i++) {
                c = sSigla.charAt(i);
                if (Character.isAlphabetic(c)) {
                    this.sSigla = sSigla;
                } else {
                    throw new IllegalArgumentException("A sigla introduzida não pode conter algarismos ou carateres especiais.");
                }
            }
        } else {
            throw new IllegalArgumentException("A sigla introduzido tem de ter pelo menos 3 caracteres");
        }

        if (sDenominacao != null && (sDenominacao.length() > 10 && sDenominacao.length() < 100)) {
            char c;
            for (int i = 0; i < sSigla.length(); i++) {
                c = sSigla.charAt(i);
                if (Character.isAlphabetic(c)) {
                    this.sDenominacao = sDenominacao;
                } else {
                    throw new IllegalArgumentException("A denominacao introduzida não pode conter algarismos ou carateres especiais.");
                }
            }
        } else {
            throw new IllegalArgumentException("A denominacao introduzido tem de ter pelo menos 3 caracteres");
        }
    }
}
