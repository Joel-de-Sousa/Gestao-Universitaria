package Sprint.WsProjeto.domain.entities;


import lombok.*;

import java.io.File;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Submissao {
    public enum Estado {PENDENTE, ACEITE, REJEITADO};
    public static final Submissao.Estado ESTADO_POR_OMISSAO = Submissao.Estado.PENDENTE;
int codSubmissao;

String titulo;

File file;

String linguagemFile;

Estado estado;

    public Submissao(String titulo, File file, String linguagemFile) {
        this.titulo = titulo;
        this.file = file;
        this.linguagemFile = linguagemFile;
    }

    public Submissao(int codSubmissao, String titulo, File file, String linguagemFile) {
        this.codSubmissao = codSubmissao;
        this.titulo = titulo;
        this.file = file;
        this.linguagemFile = linguagemFile;
    }


}
