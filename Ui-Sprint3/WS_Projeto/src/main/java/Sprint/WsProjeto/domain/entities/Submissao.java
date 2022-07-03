package Sprint.WsProjeto.domain.entities;


import lombok.*;

import java.io.File;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Submissao {

int codSubmissao;

String titulo;

File file;

String linguagemFile;

    public Submissao(String titulo, File file, String linguagemFile) {
        this.titulo = titulo;
        this.file = file;
        this.linguagemFile = linguagemFile;
    }
}
