package WSEdicao.domain.entities;

import WSEdicao.datamodel.EstudanteJpa;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
//@AllArgsConstructor
public class Edicao {

    public enum Estado {ATIVA, DESATIVA, PENDENTE};
    @Id
    private int codEdicao;
    private int uc;
    private int anoLetivo;
    private int codRUC;
    private Estado estado;
    private List<MomentoAvaliacao> momentoAvaliacaoList = new ArrayList<>();
    @Embedded
    private List<EstudanteJpa> estudantesList = new ArrayList<>();

    private static final Estado ESTADO_POR_OMISSAO =Estado.PENDENTE;

    public Edicao(int codUc, int anoLetivo, int codRUC) {
        this.uc = codUc;
        this.anoLetivo = anoLetivo;
        this.codRUC = codRUC;
        this.estado = ESTADO_POR_OMISSAO;
    }

    public Edicao(int codEdicao, int uc, int anoLetivo, int codRUC) {
        this.codEdicao = codEdicao;
        this.uc = uc;
        this.anoLetivo = anoLetivo;
        this.codRUC = codRUC;
        this.estado = ESTADO_POR_OMISSAO;
    }

    public Edicao(int codEdicao, int uc, int anoLetivo, int codRUC, Estado estado) {
        this.codEdicao = codEdicao;
        this.uc = uc;
        this.anoLetivo = anoLetivo;
        this.codRUC = codRUC;
        this.estado = estado;
    }

    public Edicao(int codEdicao, int uc, int anoLetivo, int codRUC, Estado estado, List<EstudanteJpa> estudantesList) {
        this.codEdicao = codEdicao;
        this.uc = uc;
        this.anoLetivo = anoLetivo;
        this.codRUC = codRUC;
        this.estado = estado;
        this.estudantesList = estudantesList;
    }

    public Edicao(int codEdicao, int uc, int anoLetivo, int codRUC, Estado estado, List<MomentoAvaliacao> momentoAvaliacaoList, List<EstudanteJpa> estudantesList) {
        this.codEdicao = codEdicao;
        this.uc = uc;
        this.anoLetivo = anoLetivo;
        this.codRUC = codRUC;
        this.estado = estado;
        this.momentoAvaliacaoList = momentoAvaliacaoList;
        this.estudantesList = estudantesList;
    }
}
