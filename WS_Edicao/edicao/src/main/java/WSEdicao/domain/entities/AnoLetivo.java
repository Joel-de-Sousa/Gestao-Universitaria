package WSEdicao.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import java.util.Date;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class AnoLetivo {

    @Id
    private int codAnoLetivo;
    private int ano;

    public AnoLetivo(int codAnoLetivo, int ano) {
        this.codAnoLetivo = codAnoLetivo;
        this.ano = ano;
    }
}
