package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.SubmissaoJDBC;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.stereotype.Service;

import javax.sql.rowset.CachedRowSet;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
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


    public SubmissaoJDBC toJDBC(int codSubmissao, String titulo, String urlFicheiro, String linguagemFicheiro, int estado) {
        return new SubmissaoJDBC(codSubmissao, titulo, urlFicheiro, linguagemFicheiro, estado);
    }

    public List<SubmissaoJDBC> toJDBC(CachedRowSet cachedRowSet) throws SQLException {
        List<SubmissaoJDBC> lSubmissaoJDBCs = new ArrayList<>();

        while (cachedRowSet.next()) {
            SubmissaoJDBC submissaoJDBC = toJDBC(cachedRowSet.getInt(SubmissaoJDBC.COD_SUBMISSAO),
                    cachedRowSet.getString(SubmissaoJDBC.TITULO),
                    cachedRowSet.getString(SubmissaoJDBC.URL_FICHEIRO),
                    cachedRowSet.getString(SubmissaoJDBC.LINGUAGEM_FICHEIRO),
                    cachedRowSet.getInt(SubmissaoJDBC.ESTADO));


            lSubmissaoJDBCs.add(submissaoJDBC);
        }

        return lSubmissaoJDBCs;
    }
}

