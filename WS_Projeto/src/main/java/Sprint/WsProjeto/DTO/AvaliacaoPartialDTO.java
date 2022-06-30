package Sprint.WsProjeto.DTO;

import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AvaliacaoPartialDTO {



    int codAvaliacao;
    double nota;
    String justificacao;
    String date;
    String estado;

    int codRUC;

    public AvaliacaoPartialDTO(int codAvaliacao, String estado) {
        this.codAvaliacao = codAvaliacao;
        this.estado = estado;
    }

    public AvaliacaoPartialDTO(int codAvaliacao, double nota, String justificacao, String date, int codRUC) {
        this.codAvaliacao = codAvaliacao;
        this.nota = nota;
        this.justificacao = justificacao;
        this.date = date;
        this.codRUC = codRUC;
    }
}
