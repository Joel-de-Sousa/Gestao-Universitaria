package Sprint.WsProjeto.datamodel.JPA;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projetos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjetoJPA {

    @Id
    @GeneratedValue
    private int codProjeto;


    private int codEstudante;


    private int codOrientador;


    private int codProposta;

    @OneToMany
    @JoinColumn(name = "listaAvaliacoes")
    private List<AvaliacaoJPA> listaAvaliacoes;


    public ProjetoJPA(int codEstudante, int codOrientador, int codProposta) {
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.codProposta = codProposta;

    }
}
