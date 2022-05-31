package WSEdicao.datamodel;

import lombok.Getter;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ucs")
public class UcJpa {

    @Id
    private int codUc;
    private String sSigla;
    private String sDenominacao;

    protected UcJpa(){}

    public UcJpa(int codUc, String sSigla, String sDenominacao) {
        this.codUc = codUc;
        this.sSigla = sSigla;
        this.sDenominacao = sDenominacao;
    }
}
