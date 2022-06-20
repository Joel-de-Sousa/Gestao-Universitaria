package Sprint.WsProjeto.domain.entities;


import lombok.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Avaliacao {


    int codAvaliacao;

    int codMA;

    Juri juri;

    Submissao submissao;

    public Avaliacao(int codMA, Juri juri, Submissao submissao) {
        this.codMA = codMA;
        this.juri = juri;
        this.submissao = submissao;
    }

    public Avaliacao(int codMA) {
        this.codMA = codMA;
    }
}
