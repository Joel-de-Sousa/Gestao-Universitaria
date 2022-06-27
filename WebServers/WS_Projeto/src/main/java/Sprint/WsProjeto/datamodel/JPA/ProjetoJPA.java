package Sprint.WsProjeto.datamodel.JPA;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<AvaliacaoJPA> listaAvaliacoes=new ArrayList<>();


    public ProjetoJPA(int codEstudante, int codOrientador, int codProposta) {
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.codProposta = codProposta;

    }

    public ProjetoJPA(int codProjeto, int codEstudante, int codOrientador, int codProposta) {
        this.codProjeto = codProjeto;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;
        this.codProposta = codProposta;
    }
}
