package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.SubmissaoJDBC;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Submissao;

import java.io.File;

public class SubmissaoJDBCDomainDataAssembler {
    public SubmissaoJDBC toJDBC (Submissao submissao){

        return new SubmissaoJDBC(submissao.getCodSubmissao(),submissao.getTitulo(),submissao.getFile().getPath(),submissao.getLinguagemFile(), submissao.getEstado().ordinal());
    }


    public Submissao toDomain (SubmissaoJDBC submissaoJDBC){

        File file= new File(submissaoJDBC.getUrlFicheiro());

        Submissao.Estado[] estados= Submissao.Estado.values();
        Submissao.Estado estado=estados[submissaoJDBC.getEstado()];
        return new Submissao(submissaoJDBC.getCodSubmissao(),submissaoJDBC.getTitulo(),file,submissaoJDBC.getLinguagemFicheiro(),estado);
    }
}

