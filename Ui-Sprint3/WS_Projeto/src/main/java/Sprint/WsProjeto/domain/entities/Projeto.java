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


    private int codProjeto;
    private int codProposta;
    private int codEstudante;
    private int codOrientador;

    private List<Avaliacao> listaAvaliacoes= new ArrayList<>();

    public Projeto(int codProposta, int codEstudante, int codOrientador) {
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.codProposta = codProposta;
    }

    public Projeto(int codProjeto, int codProposta, int codEstudante, int codOrientador) {
        this.codProjeto = codProjeto;
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;

    }

    public Projeto(int codProposta, int codEstudante) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;

    }
}
