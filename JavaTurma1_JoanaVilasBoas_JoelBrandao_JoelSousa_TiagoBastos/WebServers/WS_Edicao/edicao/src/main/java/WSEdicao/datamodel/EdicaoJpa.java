package WSEdicao.datamodel;

import WSEdicao.domain.entities.Edicao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Data
@Setter
@Getter
@Entity
@Table(name = "edicoes")
@AllArgsConstructor
public class EdicaoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "codEdicao_seq")
    private int codEdicao;
    private int codUc;
    private int codAnoLetivo;
    private int codRUC;
    private Edicao.Estado estado;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MomentoAvaliacaoJpa> momentoAvaliacao;
    @Embedded
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "codEdicao")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EstudanteJpa> listEstudantes;

    protected EdicaoJpa(){}

    public EdicaoJpa(int codUc, int codAnoLetivo, int codRUC, Edicao.Estado estado) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
        this.codRUC = codRUC;
        this.estado = estado;

        momentoAvaliacao = new ArrayList<MomentoAvaliacaoJpa>();
        listEstudantes = new ArrayList<EstudanteJpa>();
    }

    public EdicaoJpa(int codEdicao, int codUc, int codAnoLetivo, int codRUC, Edicao.Estado estado) {
        this.codEdicao = codEdicao;
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
        this.codRUC = codRUC;
        this.estado = estado;

        momentoAvaliacao = new ArrayList<MomentoAvaliacaoJpa>();
        listEstudantes = new ArrayList<EstudanteJpa>();
    }
}
