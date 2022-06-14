package Sprint.WsProjeto.domain.entities;


import lombok.*;

import javax.persistence.Id;
import java.util.List;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Projeto {


    private int codProjeto;


    private int codEstudante;


    private int codOrientador;


    private int codProposta;


    private List<Integer> listaAvaliacoes;

    public Projeto(int codEstudante, int codOrientador, int codProposta) {
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.codProposta = codProposta;
    }
}
