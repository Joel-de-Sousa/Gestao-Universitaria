package WSEdicao.datamodel;

import WSEdicao.utils.Util;
import lombok.Getter;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ucs")
//@Table(name = "ucs", uniqueConstraints = {@UniqueConstraint(columnNames = {"sigla"})})
public class UcJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codUc_seq")
    private int codUc;
    private String sigla;
    private String denominacao;

    protected UcJpa(){}

    public UcJpa(String sSigla, String sDenominacao) {

        if(Util.validaStringComMinSemAlgSemSimbolo(3,10,sSigla)){
            this.sigla = sSigla;
        } else {
            throw new IllegalArgumentException("A Sigla deve conter no mínimo 3 carateres, sem espaços, sem símbolos e sem algarismos");
        }
        this.denominacao = sDenominacao;
    }
}
