package WSEdicao.datamodel;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "edicoes")
public class EdicaoJpa {

    @Id
    private int codEdicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UcJpa codUc;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AnoLetivoJpa codAnoLetivo;



    protected EdicaoJpa(){}

    public EdicaoJpa(int codEdicao, UcJpa codUc, AnoLetivoJpa codAnoLetivo) {
        this.codEdicao = codEdicao;
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
    }
}
