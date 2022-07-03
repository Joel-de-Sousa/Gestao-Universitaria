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
    @GeneratedValue(strategy= GenerationType.IDENTITY/*generator = "codProjeto"*/)
    private int codProjeto;

    private int codProposta;
    private int codEstudante;


    private int codOrientador;




    @OneToMany
    @JoinColumn(name = "listaAvaliacoes")
    private List<AvaliacaoJPA> listaAvaliacoes=new ArrayList<>();


    public ProjetoJPA(int codProposta, int codEstudante, int codOrientador) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;


    }

    public ProjetoJPA(int codProjeto, int codProposta, int codEstudante, int codOrientador) {
        this.codProjeto = codProjeto;
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
        this.codOrientador = codOrientador;

    }
}
