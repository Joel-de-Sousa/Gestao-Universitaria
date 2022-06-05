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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "codEdicao_seq")
    private int codEdicao;

    //@ManyToOne(cascade=CascadeType.ALL)
    //@JoinColumn(name="ucs_coduc")
    private int codUc;


    //@ManyToOne(cascade=CascadeType.ALL)
    //@JoinColumn(name="anosletivos_codanoletivo")
    //@JoinColumn(name="codAnoLetivo")
    private int codAnoLetivo;



    protected EdicaoJpa(){}
    public EdicaoJpa(int codUc, int codAnoLetivo) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
    }
}
