package Sprint.WsProjeto.datamodel.JPA.assembler;




import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SubmissaoDomainDataAssembler {

    public SubmissaoJPA toData (Submissao submissao){

        return new SubmissaoJPA(submissao.getCodSubmissao(),submissao.getTitulo(),submissao.getFile().getPath(),submissao.getLinguagemFile());
    }


    public Submissao toDomain (SubmissaoJPA submissaoJPA){

        File file= new File(submissaoJPA.getUrlFicheiro());

        return new Submissao(submissaoJPA.getCodSubmissao(),submissaoJPA.getTitulo(),file,submissaoJPA.getLinguagemFicheiro());
    }
}
