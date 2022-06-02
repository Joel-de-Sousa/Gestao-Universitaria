package Sprint.WsProjeto.domain.entities;


import lombok.*;

import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Projeto {

    @Getter
    @Setter
private int codProjeto;

    @Getter
    @Setter

    private int codProposta;

    @Getter
    @Setter
    private int codEstudante;

    @Getter
    @Setter
    private int codOrientador;

    public Projeto(int nCodProposta, int nCodEstudante, int nCodOrientador) {
        this.codProposta = nCodProposta;
        this.codEstudante = nCodOrientador;
        this.codOrientador = nCodEstudante;
    }
}
