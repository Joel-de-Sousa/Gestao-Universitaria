package WSEdicao.datamodel;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Entity
@Table(name="anosletivos")
public class AnoLetivoJpa {

    @Id
    private int codAnoLetivo;
    private int ano;

    protected AnoLetivoJpa(){}

    public AnoLetivoJpa(int codAnoLetivo, int ano) {
        this.codAnoLetivo = codAnoLetivo;
        this.ano = ano;
    }
}
