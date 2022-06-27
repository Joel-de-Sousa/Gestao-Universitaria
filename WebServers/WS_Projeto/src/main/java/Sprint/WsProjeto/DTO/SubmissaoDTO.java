package Sprint.WsProjeto.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class SubmissaoDTO {

    @Getter

    private int codSubmissao;

    @Getter

    private String titulo;

    @Getter

    private byte[] ficheiroArray;

    @Getter

    private String linguagemFile;


}
