package Sprint.WsProjeto.datamodel.JPA;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvaliacaoJPA {

    @Id
    @GeneratedValue(generator = "codAvaliacao")
    int codAvaliacao;

    int codMA;


    @ManyToOne
    @JoinColumn(name = "juri_cod_juri")
    JuriJPA juri;

    @OneToOne
    @JoinColumn( name = "submissao_cod_submissao")
    SubmissaoJPA submissao;

    public AvaliacaoJPA(int codMA, JuriJPA juri, SubmissaoJPA submissao) {
        this.codMA = codMA;
        this.juri = juri;
        this.submissao = submissao;
    }

    public AvaliacaoJPA(int codMA) {
        this.codMA = codMA;
    }
}
