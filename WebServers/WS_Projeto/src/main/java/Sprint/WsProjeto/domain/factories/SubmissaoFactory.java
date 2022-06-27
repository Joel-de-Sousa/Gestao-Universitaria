package Sprint.WsProjeto.domain.factories;



import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class SubmissaoFactory implements ISubmissaoFactory {



    public Submissao createSubmissao(String titulo, File file, String linguagemFile) {
        return new Submissao(titulo,file,linguagemFile);
    }
}
