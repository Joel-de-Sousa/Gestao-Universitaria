package WSEdicao.datamodel;

import WSEdicao.utils.Util;
import lombok.Getter;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ucs")
public class UcJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codUc;
    private String sSigla;
    private String sDenominacao;

    protected UcJpa(){}

    public UcJpa(String sSigla, String sDenominacao) {
        if(Util.validaStringComMinSemAlgSemSimbolo(3,10,sSigla)){
            this.sSigla = sSigla;
        } else
            throw new IllegalArgumentException("A Sigla deve conter no mínimo 3 carateres, sem espaços, sem símbolos e sem algarismos");
        this.sDenominacao = sDenominacao;
    }
}
