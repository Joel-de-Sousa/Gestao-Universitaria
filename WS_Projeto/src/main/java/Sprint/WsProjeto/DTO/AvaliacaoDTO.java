package Sprint.WsProjeto.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AvaliacaoDTO {

    int codAvaliacao;

    int codProjeto;

    int codMA;

    int codJuri;

    int codSubmissao;

    double nota;

    String justificacao;

    String date;

    String estado;




}
