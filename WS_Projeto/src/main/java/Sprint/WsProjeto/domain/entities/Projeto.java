package Sprint.WsProjeto.domain.entities;


import lombok.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Projeto {
    public enum Estado {PENDENTE, CONCLUIDO}

    public static final int ORIENTADOR_POR_OMISSAO = -1;
    public static final Projeto.Estado ESTADO_POR_OMISSAO= Projeto.Estado.PENDENTE;
    private int codProjeto;

    private int codProposta;


    private int codEstudante;


    private int codOrientador;

    private Estado estado;


    public Projeto(int codProposta, int codEstudante, int codOrientador) {
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.codProposta = codProposta;
    }

    public Projeto(int codProjeto, int codProposta, int codEstudante, int codOrientador) {
        this.codProjeto = codProjeto;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.codProposta = codProposta;
    }

    public Projeto(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;

    }
}
