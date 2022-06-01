package Sprint.WsProjeto.datamodel.JPA;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projetos")
@AllArgsConstructor
public class ProjetoJPA {

    @Id
    @GeneratedValue
    @Getter
    private int codProjeto;
    @Getter
    private int codProposta;

    @Getter

    private int codEstudante;

    @Getter

    private int codOrientador;

    public ProjetoJPA() {
    }

    public ProjetoJPA(int nCodProposta, int nCodEstudante, int nCodOrientador) {
        this.codProposta = nCodProposta;
        this.codEstudante = nCodEstudante;
        this.codOrientador = nCodOrientador;
    }
}
