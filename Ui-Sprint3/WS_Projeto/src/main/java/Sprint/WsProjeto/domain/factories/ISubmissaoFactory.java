package Sprint.WsProjeto.domain.factories;



import Sprint.WsProjeto.domain.entities.Submissao;

import java.io.File;

public interface ISubmissaoFactory {

    Submissao createSubmissao(String titulo, File file, String linguagemFile);
}
