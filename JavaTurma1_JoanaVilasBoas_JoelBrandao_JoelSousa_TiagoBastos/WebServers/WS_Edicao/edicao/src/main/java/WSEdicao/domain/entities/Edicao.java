package WSEdicao.domain.entities;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Edicao {

    public enum Estado {ATIVA, DESATIVA, PENDENTE};

    private int codEdicao;
    private int uc;
    private int anoLetivo;
    private int codRUC;
    private Estado estado;

    private static final Estado ESTADO_POR_OMISSAO =Estado.PENDENTE;

    public Edicao(int codUc, int anoLetivo,int codRUC) {
        this.uc = codUc;
        this.anoLetivo = anoLetivo;
        this.codRUC = codRUC;
        this.estado = ESTADO_POR_OMISSAO;
    }

    public Edicao(int uc, int anoLetivo, int codRUC, Estado estado) {
        this.uc = uc;
        this.anoLetivo = anoLetivo;
        this.codRUC = codRUC;
        this.estado = estado;
    }

    public Edicao(int codEdicao) {
        this.codEdicao = codEdicao;
    }
}
