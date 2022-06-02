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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codEdicao_seq")
    private int codEdicao;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ucs_coduc")
    private UcJpa codUc;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="anosletivos_codanoletivo")
    private AnoLetivoJpa codAnoLetivo;



    protected EdicaoJpa(){}
    public EdicaoJpa(UcJpa codUc, AnoLetivoJpa codAnoLetivo) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
    }
}
