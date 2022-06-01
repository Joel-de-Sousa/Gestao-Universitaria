package WSEdicao.datamodel;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "edicoes")
public class EdicaoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codEdicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UcJpa codUc;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_ano_letivo",referencedColumnName = "cod")
    private AnoLetivoJpa codAnoLetivo;



    protected EdicaoJpa(){}

    public EdicaoJpa(UcJpa codUc, AnoLetivoJpa codAnoLetivo) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
    }
}
